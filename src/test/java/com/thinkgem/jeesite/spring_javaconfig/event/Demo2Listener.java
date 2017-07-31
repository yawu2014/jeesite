package com.thinkgem.jeesite.spring_javaconfig.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
public class Demo2Listener implements ApplicationListener<DemoEvent> {

	@Override
	public void onApplicationEvent(DemoEvent event) {
		String msg = event.getMsg();
		System.out.println("bean Demo2Listener 接收到了 bean demoPublisher发布的消息:"+msg);
		
	}

}
