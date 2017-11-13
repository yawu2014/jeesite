/**
 * 
 */
package com.thinkgem.jeesite.netty.protocal;

import java.util.HashMap;

import com.google.common.collect.Maps;

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
public final class Header {
	private int crcCode = 0xabef0101;
	private int length;
	private long sessionID;
	private byte type;
	private byte priority;
	private HashMap<String, Object> attachment = Maps.newHashMap();
	public final int getCrcCode() {
		return crcCode;
	}
	public void setCrcCode(int crcCode) {
		this.crcCode = crcCode;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public long getSessionID() {
		return sessionID;
	}
	public void setSessionID(long sessionID) {
		this.sessionID = sessionID;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public byte getPriority() {
		return priority;
	}
	public void setPriority(byte priority) {
		this.priority = priority;
	}
	public HashMap<String, Object> getAttachment() {
		return attachment;
	}
	public void setAttachment(HashMap<String, Object> attachment) {
		this.attachment = attachment;
	}
	@Override  
    public String toString() {  
    return "Header [crcCode=" + crcCode + ", length=" + length  
        + ", sessionID=" + sessionID + ", type=" + type + ", priority="  
        + priority + ", attachment=" + attachment + "]";  
    }  
}
