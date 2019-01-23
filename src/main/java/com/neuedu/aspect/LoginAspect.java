package com.neuedu.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class LoginAspect {

    @Pointcut("execution(public * com.neuedu.service.impl.UserServiceImpl.*(..))")
    public void pointcut(){}

//    @After("pointcut()")
//    public void after(){
//        System.out.println("登录之后");
//    }

    /**
     * 环绕通知
     * */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint){
            Object o=null;
        try{
            System.out.println("环绕之前");
            o = joinPoint.proceed();
            System.out.println("环绕之后");
        }catch(Throwable throwable){
            throwable.printStackTrace();
            System.out.println("有异常");
        }finally{
            System.out.println("最终通知");
        }
        return o;
    }


}
