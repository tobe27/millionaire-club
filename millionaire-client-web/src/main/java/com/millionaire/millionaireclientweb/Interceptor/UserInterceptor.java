package com.millionaire.millionaireclientweb.Interceptor;

import com.millionaire.millionaireclientweb.util.CookieUtil;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserInterceptor extends HandlerInterceptorAdapter {

    private ReceptionUsersService receptionUsersService;

    public UserInterceptor() {
    }

    public UserInterceptor(ReceptionUsersService receptionUsersService) {
        this.receptionUsersService = receptionUsersService;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器在方法前面运行了！");
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        if(cookie!=null){
            Long id = Long.valueOf(cookie.getValue());
            ReceptionUsers receptionUser = receptionUsersService.selectByPrimaryKey(id);
            Byte status = receptionUser.getStatus();
            if (status == 20) {
                CookieUtil.deleteCookie("cookie", "delete", response);
                response.sendRedirect(request.getContextPath()+"/loginPage");
                return false;
            }
            return true;
        }else {
             response.sendRedirect(request.getContextPath()+"/loginPage");
            return false;
        }

    }
}
