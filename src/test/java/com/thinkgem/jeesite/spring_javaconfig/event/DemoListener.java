package com.thinkgem.jeesite.spring_javaconfig.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

	@Override
	public void onApplicationEvent(DemoEvent event) {
		String msg = event.getMsg();
		System.out.println("bean demolistener 接收到了 bean demoPublisher发布的消息:"+msg);
	}

}
