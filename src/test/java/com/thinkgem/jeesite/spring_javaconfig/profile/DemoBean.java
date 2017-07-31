package com.thinkgem.jeesite.spring_javaconfig.profile;

public class DemoBean {
	private String content;
	public DemoBean(String content){
		super();
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
}
