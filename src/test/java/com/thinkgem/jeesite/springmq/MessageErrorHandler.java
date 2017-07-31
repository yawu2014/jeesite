package com.thinkgem.jeesite.springmq;

import org.apache.log4j.Logger;
import org.springframework.util.ErrorHandler;

public class MessageErrorHandler implements ErrorHandler {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Override
	public void handleError(Throwable t) {
		logger.error("RabitMQ hanpen a Error:"+t.getMessage(),t);
	}

}
