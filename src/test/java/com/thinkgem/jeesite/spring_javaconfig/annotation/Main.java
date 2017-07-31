package com.thinkgem.jeesite.spring_javaconfig.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		DemoService demo = context.getBean(DemoService.class);
		demo.outputResult();
		context.close();
	}
}
