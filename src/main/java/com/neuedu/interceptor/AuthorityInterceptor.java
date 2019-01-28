package com.neuedu.interceptor;

import com.google.gson.Gson;
import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 权限拦截器
 * */
@Component
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("============manage拦截器================");

        //实现拦截某一个控制器具体的某一个方法
        //通过handler这个对象,它里边封装了类及对应的方法
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        String className = handlerMethod.getBean().getClass().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();
        //对管理员登录接口 restful格式 放行 *****
        if(className.equals("UserManageController")&&methodName.equals("loginRestful")){
            return true;
        }
        /*===============================================*/

        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        //重构HttpServletResponse
        if(userInfo==null || userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()){
            response.reset(); //他之后在设置编码格式
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            //把获取流这句话放到if里边,否则报500错误: getWriter() has already bean called for this response
            PrintWriter printWriter = response.getWriter();
            if(userInfo == null){
                //未登录
                ServerResponse serverResponse = ServerResponse.createServerResponseByError("用户未登录");
                Gson gson = new Gson();
                String json = gson.toJson(serverResponse);
                printWriter.write(json);
            } else {
                //无权限操作
                ServerResponse serverResponse = ServerResponse.createServerResponseByError("无权限操作");
                Gson gson = new Gson();
                String json = gson.toJson(serverResponse);
                printWriter.write(json);
            }
            printWriter.flush();
            printWriter.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }


}
