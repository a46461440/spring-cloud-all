package com.zxc.apigateway.shiro.realm;

import com.zxc.apigateway.shiro.dto.JwtAuthenticationTokenDto;
import com.zxc.apigateway.shiro.macher.JwtMatcher;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author Zhou RunMing
 * @Date 2018-12-26
 */
@Component
public class RealmCompose {

    @Autowired
    private JwtMatcher jwtMatcher;

    private List<Realm> list = Arrays.asList();

    public List<Realm> getRealms() {
        this.setJwtRealm();
        return this.list;
    }

    public void setJwtRealm() {
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setCredentialsMatcher(this.jwtMatcher); //查看是否有效
        jwtRealm.setAuthenticationTokenClass(JwtAuthenticationTokenDto.class);
        this.list.add(jwtRealm);
    }

}
