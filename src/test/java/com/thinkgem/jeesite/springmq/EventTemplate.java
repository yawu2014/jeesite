package com.thinkgem.jeesite.springmq;

import com.thinkgem.jeesite.springmq.exception.SendRefuseException;

public interface EventTemplate {
	void send(String queueName,String exchangeName,Object eventContent) throws SendRefuseException;
	void send(String queueName,String exchangeName,Object eventContent,CodeFactory codeFactory) throws SendRefuseException;
}
