package com.thinkgem.jeesite.springmq.exception;

public class SendRefuseException extends Exception {
	public SendRefuseException(){
		super();
	}
	public SendRefuseException(String arg0,Throwable arg1){
		super(arg0,arg1);
	}
	public SendRefuseException(String arg0){
		super(arg0);
	}
	public SendRefuseException(Throwable arg0){
		super(arg0);
	}
}
