package com.thinkgem.jeesite.springmq;

import java.util.Map;

public interface EventController {
	void start();
	EventTemplate getEopEventTemplate();
	EventController add(String queueName,String exchangeName,EventProcesser eventProcesser);
	EventController add(Map<String,String>binding,EventProcesser eventProcesser);
}
