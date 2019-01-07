package com.zxc.user.controller;

import com.zxc.apigateway.constant.CookieConstant;
import com.zxc.apigateway.utils.jwt.JWTHelper;
import com.zxc.apigateway.utils.jwt.JwtInfo;
import com.zxc.product.enums.ResultVOStatus;
import com.zxc.user.domain.po.UserInfo;
import com.zxc.user.domain.vo.ResultVO;
import com.zxc.user.enums.UserRole;
import com.zxc.user.service.UserService;
import com.zxc.user.utils.CookieUtil;
import com.zxc.user.utils.ResultVOUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.zxc.apigateway.constant.RedisConstant.TOKEN;
import static com.zxc.apigateway.utils.jwt.Constant.EXPIRE_TIME;
import static com.zxc.product.enums.ResultVOStatus.JWT_TOKEN_ERROR;
import static com.zxc.product.enums.ResultVOStatus.LOGIN_SUCCESS;
import static com.zxc.product.enums.ResultVOStatus.USER_NOT_FOUNT;

/**
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     *
     * @param openid
     * @return
     */
    @GetMapping("/login/buyer")
    public ResultVO getBuyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        UserInfo userInfo = this.userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(USER_NOT_FOUNT);
        }
        if (!UserRole.BUYER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultVOStatus.ROLE_ERROR);
        }

        CookieUtil.set(response, CookieConstant.OPEN_ID, "ABC", CookieConstant.maxAge);
        return ResultVOUtil.success();
    }

    /**
     * 卖家登录
     *
     * @param openid
     * @return
     */
    @GetMapping("/login/seller")
    public ResultVO getSeller(@RequestParam("openid") String openid,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        String tokenInCookie = CookieUtil.get(request, CookieConstant.OPEN_ID);
        String tokenInCache = null;
        if (!StringUtils.isEmpty(tokenInCookie))
            tokenInCache = String.format(TOKEN, this.stringRedisTemplate.opsForValue().get(tokenInCookie));
        String emptyToken = String.format(TOKEN, "null");
        if (tokenInCookie != null && !StringUtils.isEmpty(tokenInCache) && !emptyToken.equals(tokenInCache)) {
            return ResultVOUtil.success();
        }
        UserInfo userInfo = this.userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(USER_NOT_FOUNT);
        }
        if (!UserRole.SELLER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultVOStatus.ROLE_ERROR);
        }
        //存入缓存
        String token = UUID.randomUUID().toString();
        Integer maxAge = CookieConstant.maxAge;
        this.stringRedisTemplate.opsForValue().set(String.format(TOKEN, "XYZ"),
                token, maxAge, TimeUnit.SECONDS);
        CookieUtil.set(response, CookieConstant.OPEN_ID, token, CookieConstant.maxAge);
        return ResultVOUtil.success();
    }

    @GetMapping("/login")
    public ResultVO login(UserInfo userInfoParam, HttpServletResponse response) {
        UserInfo userInfo = this.userService.findByUsernameAndPassword(userInfoParam.getUsername(), userInfoParam.getPassword());
        if (userInfo == null)
            return ResultVOUtil.error(USER_NOT_FOUNT);
        boolean userHasLogin = this.stringRedisTemplate.opsForValue().get(String.format(TOKEN, userInfo.getId())) != null;
        if (!userHasLogin) {
            return this.setJwtTokenInCookieAndRemoteCache(userInfo, response);
        }
        return ResultVOUtil.success();
    }

    private ResultVO setJwtTokenInCookieAndRemoteCache(UserInfo userInfo, HttpServletResponse response) {
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(userInfo.getId());
        jwtInfo.setExpiration(DateTime.now().plus(EXPIRE_TIME * 1000).toDate());
        jwtInfo.setSubject(userInfo.getUsername());
        jwtInfo.setRole("admin");
        jwtInfo.put("user", userInfo);
        try {
            String token = JWTHelper.generateToken(jwtInfo);
            CookieUtil.set(response, CookieConstant.JWT_TOKEN, token, CookieConstant.maxAge);
            this.stringRedisTemplate.opsForValue().set(String.format(TOKEN, userInfo.getId()), token, 60, TimeUnit.SECONDS);
        } catch (Exception e) {
            return ResultVOUtil.error(JWT_TOKEN_ERROR);
        }
        return ResultVOUtil.success(LOGIN_SUCCESS);
    }

    @GetMapping("/list/{name}")
    public List<UserInfo> getUserInfoByName(@PathVariable String name) {
        return this.userService.findByUsername(name);
    }

}
