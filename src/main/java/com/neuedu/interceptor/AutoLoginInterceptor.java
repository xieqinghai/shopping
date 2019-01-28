package com.neuedu.interceptor;

import com.google.gson.Gson;
import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 权限拦截器
 * */
@Component
public class AutoLoginInterceptor implements HandlerInterceptor {

    @Autowired
    IUserService userService;

    //到达controller之前 返回值true允许通过, false不允许通过
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("============preHandle==========");

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        String className = handlerMethod.getBean().getClass().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();
        if(className.equals("UserController")&&methodName.equals("loginRestful")){
            return true;
        }

        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);

        if(userInfo != null) {//从cookie中获取token信息
            return true;
        }
            Cookie[] cookies = request.getCookies();
            if(cookies != null && cookies.length>0) {
                for(Cookie cookie:cookies) {
                    String cookieName = cookie.getName();
                    if(cookieName.equals(Const.AUTOLOGINTOKEN)) {
                        String autoLoginToken = cookie.getValue();
                        //根据tookie查询用户信息
                        userInfo = userService.findUserInfoByToken(autoLoginToken);
                        if(userInfo != null) {
                            session.setAttribute(Const.CURRENTUSER,userInfo);
                        }
                        return true;
                    }
                }
            }



        response.reset(); //先重置response 防止响应两次
        //防止乱码
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        ServerResponse serverResponse = ServerResponse.createServerResponseByError(100,"需要登录");
        Gson gson = new Gson();
        String json = gson.toJson(serverResponse);
        printWriter.write(json);
        printWriter.flush();
        printWriter.close();
        return false;
    }

    /**
     * 只有当preHandle 返回为true,controller处理完,响应是会调用
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {


    }

    /**
     * 当整个请求响应完成后会调用after
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {


    }

}
