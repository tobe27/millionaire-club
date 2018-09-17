package com.millionaire.millionaireserverweb.shiro;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfiguration {

    @Resource
    private ShiroRealm shiroRealm;

    //@Bean
//public SessionManager sessionManager() {
//    DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
//    defaultWebSessionManager.setSessionDAO(redisSessionDao);
//    //可以设置shiro提供的会话管理机制
//    //defaultWebSessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
//    return defaultWebSessionManager;
//}
    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //密码加密
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        securityManager.setRealm(shiroRealm);
//        securityManager.setSessionManager(sessionManager());
//        securityManager.setCacheManager(redisCacheManager);
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登陆页的URL
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //未认证的跳转页
        //自定义过滤器
//        Map<String,Filter> filterMap=new LinkedHashMap<>();
//        shiroFilterFactoryBean.setFilters(filterMap);
        //权限控制map
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/backstageLogin","anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**","authc");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了

//        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//        filterChainDefinitionMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);



        //过滤器链
//        shiroFilterFactoryBean.setFilterChainDefinitions("/login = anon");
//        shiroFilterFactoryBean.setFilterChainDefinitions("/subBackstageLogin = anon");
//        shiroFilterFactoryBean.setFilterChainDefinitions("/a/** = authc");//经过认证后才能访问相对的路径
        return shiroFilterFactoryBean;
    }

//    对密码加密
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("UnauthorizedException", "/403");
        mappings.setProperty("UnauthenticatedException", "/403");
        simpleMappingExceptionResolver.setExceptionMappings(mappings);
        return simpleMappingExceptionResolver;
    }
}

