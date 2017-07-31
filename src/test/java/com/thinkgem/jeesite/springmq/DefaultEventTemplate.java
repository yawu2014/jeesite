package com.thinkgem.jeesite.springmq;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;

import com.alibaba.druid.util.StringUtils;
import com.thinkgem.jeesite.springmq.exception.SendRefuseException;

public class DefaultEventTemplate implements EventTemplate {
	private final Logger logger = Logger.getLogger(this.getClass());
	private AmqpTemplate eventAmqpTemplate;
	private CodeFactory defaultCodeFactory;
	private DefaultEventController eec;
	public DefaultEventTemplate(AmqpTemplate eopAmqpTemplate,CodeFactory defaultCodeFactory,DefaultEventController eec){
		this.eventAmqpTemplate = eopAmqpTemplate;
		this.defaultCodeFactory = defaultCodeFactory;
		this.eec = eec;
	}
	public DefaultEventTemplate(AmqpTemplate eopAmqpTemplate,CodeFactory defaultCodeFactory){
		this.eventAmqpTemplate = eopAmqpTemplate;
		this.defaultCodeFactory = defaultCodeFactory;
	}
	@Override
	public void send(String queueName,String exchangeName,Object eventContent) throws SendRefuseException{
		this.send(queueName, exchangeName, eventContent,defaultCodeFactory);
	}
	@Override
	public void send(String queueName,String exchangeName,Object eventContent,CodeFactory codeFacoty) throws SendRefuseException{
		if(StringUtils.isEmpty(queueName)||StringUtils.isEmpty(exchangeName)){
			throw new SendRefuseException("queueName exchangeName can't be null");
		}
		byte[] eventContentBytes = null;
		if(codeFacoty == null){
			if(eventContent == null){
				logger.warn("Find eventContent is null,are you sure...");
			}else{
				throw new SendRefuseException("codeFacoty nust not be null,unless eventContent is null");
			}
		}else{
			try{
				eventContentBytes = codeFacoty.serialize(eventContent);//获取序列化的数据
			}catch(IOException e){
				throw new SendRefuseException(e);
			}
		}
		EventMessage msg = new EventMessage(queueName,exchangeName,eventContentBytes);
		try{
			eventAmqpTemplate.convertAndSend(exchangeName,queueName,msg);
		}catch(AmqpException e){
			logger.error("send Event Failed"+eventContent+"]",e);
			throw new SendRefuseException("send Event failed",e);
		}
	}
}
