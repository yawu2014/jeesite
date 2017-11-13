/**
 * 
 */
package com.thinkgem.jeesite.netty.protocal;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年9月15日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class NettyMessage {
	private Header header;
	private Object body;
	public final Header getHeader() {
		return header;
	}
	public final Object getBody() {
		return body;
	}
	public final void setBody(Object body) {
		this.body = body;
	}
	public final void setHeader(Header header) {
		this.header = header;
	}
	@Override  
    public String toString() {  
        return "NettyMessage [header=" + header + "]";  
    }  
}
