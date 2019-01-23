package com.neuedu;

import com.neuedu.interceptor.AutoLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册拦截器
 * 拦截器已关闭
 * */
@SpringBootConfiguration
public class RegisterInterceptor implements WebMvcConfigurer {

    @Autowired
    private AutoLoginInterceptor autoLoginInterceptor;

    /**
     * addInterceptor()可以多个,注册多个拦截器
     *   addPathPatterns添加需要拦截的命名空间；
     *   excludePathPatterns添加排除拦截命名空间
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         *  /protal/* 拦截一层目录
         *  /portal/** 拦截所有子目录
         *
         * 自动登录 需要拦截 /portal/user/get_information.do
         *   /portal/cart/add.do ...
         *   /portal/order/create.do ..
        **/
        //排除拦截的目录
        List<String> excludeList = new ArrayList<>();
        excludeList.add("/user/login.do");
        excludeList.add("/user/register.do");
        excludeList.add("/user/logout.do");
        excludeList.add("/product/**");
        registry.addInterceptor(autoLoginInterceptor).addPathPatterns("/user/**").excludePathPatterns(excludeList);
        registry.addInterceptor(autoLoginInterceptor).addPathPatterns("/cart/**");
    }

}
