/**
 * 
 */
package com.thinkgem.jeesite.netty.protocal;

import java.io.IOException;

import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;
import org.jboss.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import org.jboss.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import org.jboss.netty.handler.codec.marshalling.MarshallerProvider;
import org.jboss.netty.handler.codec.marshalling.MarshallingDecoder;
import org.jboss.netty.handler.codec.marshalling.MarshallingEncoder;
import org.jboss.netty.handler.codec.marshalling.UnmarshallerProvider;

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
public class NettyMessageMarshallingCodecFactory {
	/**
	 * 
	 * <p>
	 * 构建解码器
	 * </p>
	 * 
	 * @author liuyujian
	 * @date 2017年9月15日 上午10:39:44
	 * @return
	 */
	public static MarshallingDecoder buildMarshallingDecoder() {
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
		MarshallingDecoder decoder = new MarshallingDecoder(provider,1024);
		return decoder;
	}
	/**
	 * 
	 * <p>
	 * 创建编码器
	 * </p>
	 * 
	 * @author liuyujian
	 * @date 2017年9月15日 上午10:45:09
	 * @return
	 */
	public static MarshallingEncoder buildMarshallingEncoder() {
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory,configuration);
		MarshallingEncoder encoder = new MarshallingEncoder(provider);
		return encoder;
	}
	
	public static Marshaller buildMarshalling() throws IOException{
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		Marshaller marshaller = marshallerFactory.createMarshaller(configuration);
		return marshaller;
	}
	
	public static Unmarshaller buildUnMarshalling() throws IOException{
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		final Unmarshaller unmarshaller = marshallerFactory.createUnmarshaller(configuration);
		return unmarshaller;
	}
}
