package com.thinkgem.jeesite.spring_javaconfig.event;

import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent {
	private static final long serialVersionUID = 4416882497453127704L;
	private String msg;
	public DemoEvent(Object source,String msg) {
		super(source);
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
