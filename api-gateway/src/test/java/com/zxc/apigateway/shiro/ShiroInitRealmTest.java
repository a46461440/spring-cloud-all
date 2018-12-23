package com.zxc.apigateway.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/23
 */
public class ShiroInitRealmTest {

    private IniRealm iniRealm;

    private DefaultSecurityManager securityManager;

    @Before
    public void init() {
        this.securityManager=new DefaultSecurityManager();
        this.iniRealm = new IniRealm("classpath:user.ini");
        this.securityManager.setRealm(this.iniRealm);
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
        System.out.println(subject.isPermitted("user:update"));
        System.out.println(subject.isPermitted("user:select"));
        SecurityUtils.getSubject().logout();
        System.out.println(String.valueOf(subject.isAuthenticated()));
    }

}
