package com.thinkgem.jeesite.springmq;

import java.io.Serializable;
import java.util.Arrays;

public class EventMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5885692798042312778L;
	private String queueName;
	private String exchangeName;
	private byte[] eventData;
	public EventMessage(String queueName,String exchangeName,byte[] eventData){
		this.queueName = queueName;
		this.exchangeName = exchangeName;
		this.eventData = eventData;
	}
	public EventMessage(){
		
	}
	public String getQueueName(){
		return queueName;
	}
	public String getExchangeName(){
		return exchangeName;
	}
	public byte[] getEventData(){
		return eventData;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "EopEventMessage [queueName=" + queueName + ", exchangeName="  
                + exchangeName + ", eventData=" + Arrays.toString(eventData)  
                + "]";
	}
	
}
