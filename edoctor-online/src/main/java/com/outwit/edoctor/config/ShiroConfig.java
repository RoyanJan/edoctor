package com.outwit.edoctor.config;

import com.outwit.edoctor.domain.UserCredentialMatcher;
import com.outwit.edoctor.domain.UserRealm;
import com.outwit.edoctor.service.UserService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.outwit.edoctor")
public class ShiroConfig {

    @Autowired
    UserService userService;


    @Bean(name = "userRealm")
    public UserRealm getUserRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setUserService(userService);
        userRealm.setCredentialsMatcher(new UserCredentialMatcher());
        return userRealm;
    }

    @Bean(name = "securityManager")
    public SecurityManager getSecurityManager() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(getUserRealm());
        return securityManager;
    }

    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(new Object[]{
                getSecurityManager()
        });
        return methodInvokingFactoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
