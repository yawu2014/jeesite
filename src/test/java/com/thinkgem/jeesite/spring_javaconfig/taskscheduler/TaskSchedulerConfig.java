package com.thinkgem.jeesite.spring_javaconfig.taskscheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.thinkgem.jeesite.spring_javaconfig.taskscheduler")
@EnableScheduling
public class TaskSchedulerConfig {

}
