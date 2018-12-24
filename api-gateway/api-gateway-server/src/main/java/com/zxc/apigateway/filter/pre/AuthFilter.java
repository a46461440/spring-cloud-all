package com.zxc.apigateway.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zxc.apigateway.constant.RedisConstant;
import com.zxc.apigateway.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限拦截区分买家和卖家
 *
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
@Component
public class AuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        /**
         * /order/create只能买家访问 (cookie里有openid)
         * /order/finish只能卖家访问 (cookie里有token,并且cache中有值)
         * /product/list都可以访问
         */
        if (request.getRequestURI().contains("/order/create")) {
            String value = CookieUtil.get(request, "openid");
            if (value == null) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        } else if (request.getRequestURI().contains("/order/finish")) {
            String value = CookieUtil.get(request, "token");
            if (value == null) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
            String valueInRedis = this.stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN, value));
            if (StringUtils.isEmpty(valueInRedis)) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }
        return null;
    }
}
