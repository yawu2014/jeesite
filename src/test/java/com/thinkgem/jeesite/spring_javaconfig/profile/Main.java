package com.thinkgem.jeesite.spring_javaconfig.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("prod");
		context.register(ProfileConfig.class);
		context.refresh();
		DemoBean bean = context.getBean(DemoBean.class);
		System.out.println(bean.getContent());
		context.close();
	}

}
