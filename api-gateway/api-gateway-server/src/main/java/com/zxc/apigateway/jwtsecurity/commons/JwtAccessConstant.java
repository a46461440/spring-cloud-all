package com.zxc.apigateway.jwtsecurity.commons;

/**
 * @author Zhou RunMing
 * @Date 2019-1-8
 */
public interface JwtAccessConstant {


    String CREDENTIAL_NOT_TRUSTED_WARNING = "the credentials are not sufficiently trusted, you need to login";

    String UNAUTHORIZED_WARNING = "you are not allowed to visit this url:%s";

    String EXPIRED_WARNING = "your should login again";

    String ROLE_NOT_FOUNT_WARNING = "the roles of your account don't exit";

}
