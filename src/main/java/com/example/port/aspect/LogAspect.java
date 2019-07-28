package com.example.port.aspect;

import com.example.port.annotation.Oplog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {
    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.example.port.annotation.Oplog)")
    public void controllerAllMethod() {
        // Do nothing because of .
    }

    @Around("controllerAllMethod()")
    public Object process(ProceedingJoinPoint jp) throws Throwable {
        String targetName = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Oplog.class) && method.getName().equals(methodName)) {
                String trim = method.getAnnotation(Oplog.class).message().trim();
                System.out.println(trim);
            }
        }
        return jp.proceed();
    }
}
