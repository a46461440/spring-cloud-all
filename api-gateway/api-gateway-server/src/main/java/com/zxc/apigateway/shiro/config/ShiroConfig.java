package com.zxc.apigateway.shiro.config;

import com.zxc.apigateway.shiro.filter.ShiroFilterChainManager;
import com.zxc.apigateway.shiro.realm.RealmCompose;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code shiro}基础配置
 *
 * @author Zhou RunMing
 * @Date 2018-12-26
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置{@link ShiroFilter}过滤器 并将{@link SecurityManager}里的{@link Realm}设置进去
     * 将{@link ShiroFilter}过滤器链设置进去
     * @param securityManager shiro安全管理器
     * @param filterChainManager shiro过滤器链
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, ShiroFilterChainManager filterChainManager) {
        RestShiroFilterFactoryBean shiroFilterFactoryBean = new RestShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setFilters(filterChainManager.initGetFilters());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainManager.initGetFilterChain());
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(RealmCompose realmCompose) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setAuthenticator(new AModularRealmAuthenticator());
        securityManager.setRealms(realmCompose.getRealms());

        // 无状态subjectFactory设置
//        DefaultSessionStorageEvaluator evaluator = (DefaultSessionStorageEvaluator)((DefaultSubjectDAO) securityManager.getSubjectDAO()).getSessionStorageEvaluator();
//        evaluator.setSessionStorageEnabled(Boolean.FALSE);
//        StatelessWebSubjectFactory subjectFactory = new StatelessWebSubjectFactory();
//        securityManager.setSubjectFactory(subjectFactory);

        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }
}
