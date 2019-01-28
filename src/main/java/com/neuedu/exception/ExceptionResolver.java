package com.neuedu.exception;

import com.neuedu.common.ResponseCode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * 全局异常处理类
 * 需要让spring容器管理这个类,才会起作用
 * */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        //为了调试,打印堆栈信息, 项目上线后需要注释掉这句
        e.printStackTrace();

        //new MappingJackson2JsonView() 为了将ModelAndView转成json格式
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());

        //封装与ServerResponse相同字段
        modelAndView.addObject("status", ResponseCode.ERROR);
        modelAndView.addObject("msg","接口调用出错");
        modelAndView.addObject("data",e.toString());

        return modelAndView;
    }
}
