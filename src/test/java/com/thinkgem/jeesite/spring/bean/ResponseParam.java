package com.thinkgem.jeesite.spring.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p>
 * 服务器响应参数
 * </p>
 * 
 * @author Lennon.Wang
 * @site http://www.grablove.com  
 * @version 1.0
 * @date 2017-2-13 
 * @since JDK 1.7
 * @copyright Copyright 2017 BeiJing MMEDNET. All rights reserved.
 */
public class ResponseParam implements Serializable {

	private static final long serialVersionUID = 8406254240070330741L;

	/** 成功 **/
	public static final int STATUS_SUCCESS = 0;

	/** 失败 **/
	public static final int STATUS_ERROR = 1;

	/** JSON数据 **/
	private Object data = new ArrayList<String>();

	/** 状态0正常、1失败 **/
	private int status = 0;

	/** 提示信息 **/
	private String msg = "";

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
		if (null == data) {
			this.data = new ArrayList<String>();
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
