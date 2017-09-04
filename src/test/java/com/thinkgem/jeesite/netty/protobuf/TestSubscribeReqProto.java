/**
 * 
 */
package com.thinkgem.jeesite.netty.protobuf;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.google.protobuf.InvalidProtocolBufferException;

import groovyjarjarantlr.collections.List;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年9月4日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class TestSubscribeReqProto {
	private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
		return req.toByteArray();
	}
	private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException{
		return SubscribeReqProto.SubscribeReq.parseFrom(body);
	}
	private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqId(1);
		builder.setUsername("Luck");
		builder.setProductName("Netty Book");
		ArrayList<String> address = Lists.newArrayList();
		address.add("NanJing YuHuaTai");
		builder.addAllAddress(address);
		return builder.build();
	}
	public static void main(String[] args) throws InvalidProtocolBufferException {
		SubscribeReqProto.SubscribeReq req = createSubscribeReq();
		System.out.println("Before encode:"+req.toString());
		SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
		System.out.println("After decode:"+req2.toString());
		System.out.println("Assert equals"+req.equals(req2));
	}
}
