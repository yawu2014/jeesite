package com.thinkgem.jeesite.spring_javaconfig.prepost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig.class);
		BeanWayService beanWayService = context.getBean(BeanWayService.class);
		JSR250WayService jsr250Service = context.getBean(JSR250WayService.class);
		context.close();
	}

}
