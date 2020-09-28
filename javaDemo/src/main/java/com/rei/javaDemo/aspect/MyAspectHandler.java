package com.rei.javaDemo.aspect;

import cn.hutool.core.date.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * 自定义注解处理类
 */
@Aspect
@Component//把这个类交给spring管理
@Slf4j
public class MyAspectHandler {

    // 把切面的连接点放在了我们的注解上
    @Pointcut("@annotation(com.rei.javaDemo.aspect.MyAspect)")
    public void ouAspect() {
    }

    // 在这里定义前置切面
    @Before("ouAspect()")
    public void beforeMethod(JoinPoint joinPoint) {

        // 这里执行保存日志的动作
        System.out.println("方法前.......");
        //得到被切方法的参数
        if (joinPoint.getArgs().length > 0){
            System.out.println(joinPoint.getArgs()[0]);
        }
    }

    /**
     * 环绕切面
     */
    @Around("ouAspect()")
    public Object  aroundMethod(ProceedingJoinPoint point) throws Throwable {
        // 获取sessionid
        ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String sessionId = request.getSessionId();

        StopWatch watch = new StopWatch();
        watch.start();

        Signature s = point.getSignature();
        // 获取注解参数
        MethodSignature methodSignature = (MethodSignature) s;//获取参数名
        // 如果带有注解参数，方便去找注解参数
        MyAspect myAspect = methodSignature.getMethod().getAnnotation(MyAspect.class);
        String a = myAspect.value();
        String p = myAspect.path();

        String methodName = s.getName();
        Object obj = point.proceed();
        watch.stop();
        System.out.println(methodName+"方法执行时间="+watch.getTotalTimeMillis());
        return obj;
    }

}
