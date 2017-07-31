package com.thinkgem.jeesite.spring_javaconfig.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	@Pointcut("@annotation(com.thinkgem.jeesite.spring_javaconfig.aop.Action)")
	public void annotationPointCut(){};
	@After("annotationPointCut()")
	public void after(JoinPoint joinPoint){
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		Action action = method.getAnnotation(Action.class);
		System.out.println("注解拦截:"+action.name());
	}
	@Before("execution(* com.thinkgem.jeesite.spring_javaconfig.aop.DemoMethodService.*(..))")
	public void before(JoinPoint joinPoint){
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		System.out.println("方法拦截式拦截:"+method.getName());
	}
}
