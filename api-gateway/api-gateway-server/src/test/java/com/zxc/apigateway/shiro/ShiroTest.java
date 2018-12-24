package com.zxc.apigateway.shiro;

import com.zxc.apigateway.ApiGatewayApplicationTests;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/23
 */
@Component
public class ShiroTest extends ApiGatewayApplicationTests {

    private DefaultSecurityManager securityManager = new DefaultSecurityManager();

    private SimpleAccountRealm realm = new SimpleAccountRealm();

    @Before
    public void init() {
        this.securityManager.setRealm(this.realm);
        this.realm.addAccount("zxc", "Aa412629", "admin");
    }

    @Test
    public void testShiroLogin() {
        SecurityUtils.setSecurityManager(this.securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zxc", "Aa412629");
        try {
            subject.login(token);
        } catch(UnknownAccountException e) {
            System.out.println("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        }
        subject.checkRole("admin");
        System.out.println(String.valueOf(subject.hasRole("admin")));
        System.out.println(String.valueOf(subject.isAuthenticated()));
        SecurityUtils.getSubject().logout();
        System.out.println(String.valueOf(subject.isAuthenticated()));
    }

}
