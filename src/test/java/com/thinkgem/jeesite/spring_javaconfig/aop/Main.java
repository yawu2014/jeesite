package com.thinkgem.jeesite.spring_javaconfig.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fasterxml.classmate.AnnotationConfiguration;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
		DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
		demoAnnotationService.add();
		demoMethodService.add();
		context.close();
	}

}
