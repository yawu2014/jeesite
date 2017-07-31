package com.thinkgem.jeesite.springmq;

import java.io.IOException;

public interface CodeFactory {
	byte[] serialize(Object obj) throws IOException;
	Object deSerialize(byte[] in) throws IOException;
}
