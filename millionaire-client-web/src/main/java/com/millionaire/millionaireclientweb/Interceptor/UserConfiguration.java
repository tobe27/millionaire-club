package com.millionaire.millionaireclientweb.Interceptor;

import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class UserConfiguration implements WebMvcConfigurer {
    @Resource
    private ReceptionUsersService receptionUsersService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor(receptionUsersService)).addPathPatterns("/u/**");
    }





}
