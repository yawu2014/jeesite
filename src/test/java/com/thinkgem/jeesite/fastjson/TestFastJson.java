package com.thinkgem.jeesite.fastjson;

import org.activiti.engine.impl.util.json.JSONTokener;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.JSONToken;

public class TestFastJson {
	/**
	 * JSON.parse(String str)strshi JSONObject 或者JSONArray时才会正常否则会报错
	 */
	@Test
	public void testArrayOrObj(){
		String str = "{\"data\":\"/static/media/uploads/chatimage/1493805772654.jpg\",\"status\":0,\"msg\":\"\"}";
		JSONObject obj = JSONObject.parseObject(str);
//		String array = obj.getString("data");
		System.out.println(obj.getString("data"));
		Object token = JSON.parse(obj.getString("data"));//new JSONTokener(obj.getString("data")).nextValue();
		if(token instanceof JSONObject){
			System.out.println("object");
		}else if(token instanceof JSONArray){
			System.out.println("jsonArray");
		}else if(token instanceof String){
			System.out.println("String");
		}
		System.out.println("success:"+token.getClass());
	}
}
