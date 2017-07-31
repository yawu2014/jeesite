package com.thinkgem.jeesite.springmq;

import com.caucho.hessian.io.HessianFactory;

public class EventControlConfig {
	private final static int DEFAULT_PORT = 5672;
	private final static String DEFAULT_USERNAME = "guest";
	private final static String DEFAULT_PASSWORD = "guest";
	private final static int DEFAULT_PROCESS_THREAD_NUM = Runtime.getRuntime().availableProcessors()*2;
	private static final int PREFFETCH_SIZE = 1;
	private String serverHost;
	private int port = DEFAULT_PORT;
	private String username = DEFAULT_USERNAME;
	private String password = DEFAULT_PASSWORD;
	private String virtualHost;
	private int connectionTimeout = 0;
	private int eventMsgProcessNum;
	private int prefetchSize;
	public EventControlConfig(String serverHost){
		this(serverHost,DEFAULT_PORT,DEFAULT_USERNAME,DEFAULT_PASSWORD,null,0,DEFAULT_PROCESS_THREAD_NUM,DEFAULT_PROCESS_THREAD_NUM,new HessianCodeFactory());
	}
	public EventControlConfig(String serverHost,int port,String username,String password,String virtualHost,int connectionTimeout,int eventMsgProcessNum,int prefetchSize,CodeFactory defaultCodeFactory){
		this.serverHost = serverHost;
		this.port = port>0?port:DEFAULT_PORT;
		this.username = username;
		this.password = password;
		this.virtualHost = virtualHost;
		this.connectionTimeout = connectionTimeout>0?connectionTimeout:0;
		this.eventMsgProcessNum = eventMsgProcessNum>0?eventMsgProcessNum:DEFAULT_PROCESS_THREAD_NUM;
		this.prefetchSize = prefetchSize>0?prefetchSize:PREFFETCH_SIZE;
		
	}
	public String getServerHost() {
		return serverHost;
	}
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVirtualHost() {
		return virtualHost;
	}
	public void setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
	}
	public int getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	public int getEventMsgProcessNum() {
		return eventMsgProcessNum;
	}
	public void setEventMsgProcessNum(int eventMsgProcessNum) {
		this.eventMsgProcessNum = eventMsgProcessNum;
	}
	public int getPrefetchSize() {
		return prefetchSize;
	}
	public void setPrefetchSize(int prefetchSize) {
		this.prefetchSize = prefetchSize;
	}
	
}
