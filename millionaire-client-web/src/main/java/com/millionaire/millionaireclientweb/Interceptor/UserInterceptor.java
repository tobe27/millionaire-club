package com.millionaire.millionaireclientweb.Interceptor;

import com.millionaire.millionaireclientweb.util.CookieUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器在方法前面运行了！");
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        if(cookie!=null){
            return true;
        }else {
             response.sendRedirect(request.getContextPath()+"/loginPage");
            return false;
        }

    }
}
