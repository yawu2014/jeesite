/**
 * 
 */
package com.thinkgem.jeesite.netty.protocal;

import java.io.IOException;

import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

import io.netty.buffer.ByteBuf;

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
public class NettyMessageMarshallingDecoder {
	private final Unmarshaller unmarshaller;
	public NettyMessageMarshallingDecoder() throws IOException {
		unmarshaller = NettyMessageMarshallingCodecFactory.buildUnMarshalling();
	}
	
	protected Object decode(ByteBuf in) throws Exception {
		int objectSize = in.readInt();
		ByteBuf buf = in.slice(in.readerIndex(),objectSize);
		ByteInput input = new ChannelBufferByteInput(buf);
		try {
			unmarshaller.start(input);
			Object obj = unmarshaller.readObject();
			unmarshaller.finish();
			in.readerIndex(in.readerIndex()+objectSize);
			return obj;
		}finally {
			unmarshaller.close();
		}
	}
}
