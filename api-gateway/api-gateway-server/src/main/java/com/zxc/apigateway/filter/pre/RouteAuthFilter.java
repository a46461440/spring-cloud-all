package com.zxc.apigateway.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zxc.apigateway.utils.CookieUtil;
import com.zxc.apigateway.utils.jwt.JWTHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.zxc.apigateway.constant.CookieConstant.JWT_TOKEN;
import static com.zxc.apigateway.constant.RedisConstant.TOKEN;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 根据{@link Cookie}的{@coke token}获取用户信息
 * 校验是否过期与是否具备权限访问url
 *
 * @author Zhou RunMing
 * @Date 2018-12-24
 */
@Slf4j
@Component
public class RouteAuthFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (request.getRequestURI().contains("user/login"))
            return false;
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        String jwtToken = CookieUtil.get(request, JWT_TOKEN);
        Jws<Claims> jws = null;
        try {
            jws = JWTHelper.parserToken(jwtToken);
        } catch (ExpiredJwtException e) {
            this.log.error(jwtToken + "超时:" + e.getMessage());
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        } catch (Exception e) {
            this.log.error(e.getMessage());
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        if (jws != null) { //如果token在cache中也存在的话则证明正常
            Claims claims = jws.getBody();
            String keyInRemoteCache = String.format(TOKEN, claims.getId());
            jwtToken = this.stringRedisTemplate.opsForValue().get(keyInRemoteCache);
            if (StringUtils.isEmpty(jwtToken)) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }
        return null;
    }


}
