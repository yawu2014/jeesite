package com.thinkgem.jeesite.springmq;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class MessageAdapterHandler {
	private final Logger logger = Logger.getLogger(this.getClass());
	private ConcurrentMap<String,EventProcessorWrap> epwMap;
	public MessageAdapterHandler(){
		this.epwMap = new ConcurrentHashMap<String,EventProcessorWrap>();
	}
	public void handleMessage(EventMessage em){
		logger.debug("Receive an EventMessage ["+em+"]");
		if(em == null){
			logger.warn("Receive an null EventMessage");
			return;
		}
		if(StringUtils.isEmpty(em.getQueueName())){
			logger.warn("the EventMessage's queueName is null");;
			return;
		}
		EventProcessorWrap eepw = epwMap.get(em.getQueueName()+"|"+em.getExchangeName());
		if(eepw == null){
			logger.warn("Receiver an EopEventMessage");
			return;
		}
		try{
			eepw.process(em.getEventData());
		}catch(IOException e){
			logger.error("Event content can not be Deserialized");
			return;
		}
		
	}
	protected void add(String queueName,String exchangeName,EventProcesser processor,CodeFactory codeFactory){
		if(StringUtils.isEmpty(queueName) || StringUtils.isEmpty(exchangeName)||processor == null || codeFactory == null){
			throw new RuntimeException("参数不能为空");
		}
		EventProcessorWrap epw = new EventProcessorWrap(codeFactory,processor);
		EventProcessorWrap oldProcessorWrap = epwMap.putIfAbsent(queueName+"|"+exchangeName, epw);
		if(oldProcessorWrap != null){
			logger.warn("The Processor of this queue already Exists");
		}
	}
	protected Set<String>getAllBinding(){
		Set<String>keySet = epwMap.keySet();
		return keySet;
	}
	protected static class EventProcessorWrap{
		private CodeFactory codeFactory;
		private EventProcesser eep;
		protected EventProcessorWrap(CodeFactory codeFactory,EventProcesser eep){
			this.codeFactory = codeFactory;
			this.eep = eep;
		}
		public void process(byte[] eventData) throws IOException{
			Object obj = codeFactory.deSerialize(eventData);
			eep.process(obj);
		}
	}
	
}
