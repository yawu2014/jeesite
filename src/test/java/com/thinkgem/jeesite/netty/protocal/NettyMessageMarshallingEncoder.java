/**
 * 
 */
package com.thinkgem.jeesite.netty.protocal;

import java.io.IOException;

import org.jboss.marshalling.Marshaller;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;  
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
@Sharable
public class NettyMessageMarshallingEncoder {
	//长度占位符
	private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
	Marshaller marshaller;
	
	public NettyMessageMarshallingEncoder() throws IOException{
		marshaller = NettyMessageMarshallingCodecFactory.buildMarshalling();
	}
	protected void encode(Object msg,ByteBuf out) throws IOException{
		try {
			int lengthPos = out.writerIndex();
			out.writeBytes(LENGTH_PLACEHOLDER);//写入长度占位
			ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);
			marshaller.start(output);
			marshaller.writeObject(msg);
			marshaller.finish();
			out.setInt(lengthPos,out.writerIndex()-lengthPos-4);//更改对象长度,不包含"长度"字段的长度
		}finally {
			marshaller.close();
		}
	}
}
