package com.neuedu.aspect;

import com.neuedu.common.ServerResponse;
import com.neuedu.json.ObjectMapperApi;
import com.neuedu.redis.RedisApi;
import com.neuedu.utils.MD5Utils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * redis缓存切面类
 * */
@Component
@Aspect
public class RedisCacheAspect {

    /**
     * 定义切入点
     * */
    @Pointcut("execution(* com.neuedu.service.impl.ProductServiceImpl.*(..))")
    public void pointcut(){}

    @Autowired
    RedisApi redisApi;

    @Autowired
    ObjectMapperApi objectMapperApi;


    /**
     * 定义环绕通知
     * */
    @Around("pointcut()")
    public Object arround(ProceedingJoinPoint joinPoint){

        Object object = null;

        try {
            //key:MD5(全类名+方法名+参数)
            StringBuffer keyBuffer = new StringBuffer();
            //全类名
            String className = joinPoint.getTarget().getClass().getName(); // getTarget()获取目标方法类
            keyBuffer.append(className);
            //获取目标方法的方法名
            String methodName = joinPoint.getSignature().getName(); //getSignature()获取目标方法

            // 写 会清空缓存
            if(methodName.equals("saveOrUpdate") || methodName.equals("set_sale_status") || methodName.equals("upload")){
                redisApi.flushDB();
            }

            keyBuffer.append(methodName);
            //方法中的参数
            Object[] objects = joinPoint.getArgs();
            if(objects != null){
                for(Object arg:objects){
                    keyBuffer.append(arg);
                }
            }
            //step1:读缓存     MD5加密
            String key = MD5Utils.getMD5Code(keyBuffer.toString());
            String json = redisApi.get(key);
            if(json!=null && !json.equals("")){
                //System.out.println("========读取到了缓存==========");
                return objectMapperApi.str2Obj(json, ServerResponse.class);
            }
            //step2:查询数据库
            //执行目标方法
            object = joinPoint.proceed();
            //System.out.println("=========读取数据库=======");
            //step3:将查询结果写入缓存
            if(object != null){
                String jsoncache = objectMapperApi.obj2str(object);
                redisApi.set(key,jsoncache);
                //System.out.println("=========将数据库内容写入缓存=========");
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return object;
    }




}
