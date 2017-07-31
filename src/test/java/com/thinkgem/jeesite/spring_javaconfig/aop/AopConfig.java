package com.thinkgem.jeesite.spring_javaconfig.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.thinkgem.jeesite.spring_javaconfig.aop")
@EnableAspectJAutoProxy
public class AopConfig {

}
