package com.thinkgem.jeesite.springmq;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SerializerMessageConverter;

public class DefaultEventController implements EventController {
	private CachingConnectionFactory rabbitConnectionFactory;
	private EventControlConfig config;
	private RabbitAdmin rabbitAdmin;
	private CodeFactory defaultCodeFactory = new HessianCodeFactory();
	private SimpleMessageListenerContainer msgListenerContainer;
	private MessageAdapterHandler msgAdapterHandler = new MessageAdapterHandler();
	
	private MessageConverter serializerMessageConverter = new SerializerMessageConverter();
	private Map<String,DirectExchange> exchanges = new HashMap<String,DirectExchange>();//缓存使用
	private Map<String,Queue>queues = new HashMap<String,Queue>();
	
	private Set<String> binded = new HashSet<String>();
	private EventTemplate eventTemplate;
	private AtomicBoolean isStarted = new AtomicBoolean(false);
	private static DefaultEventController defaultEventController;
	public synchronized static DefaultEventController getInstance(EventControlConfig config){
		if(defaultEventController == null){
			defaultEventController = new DefaultEventController(config);
		}
		return defaultEventController;
	}
	/**
	 * 根据config初始化ConnectionFactory
	 * @param config
	 */
	private DefaultEventController(EventControlConfig config){
		if(config == null){
			throw new IllegalArgumentException("config can't be null");
		}
		this.config = config;
		initRabbitConnectionFactory();//初始化参数
		rabbitAdmin = new RabbitAdmin(rabbitConnectionFactory);
		RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
		rabbitTemplate.setMessageConverter(serializerMessageConverter);
		eventTemplate = new DefaultEventTemplate(rabbitTemplate,defaultCodeFactory,this);//获取EventTemplate
	}
	private void initRabbitConnectionFactory(){
		rabbitConnectionFactory = new CachingConnectionFactory();
		rabbitConnectionFactory.setHost(config.getServerHost());
		rabbitConnectionFactory.setChannelCacheSize(config.getEventMsgProcessNum());;
		rabbitConnectionFactory.setPort(config.getPort());
		rabbitConnectionFactory.setUsername(config.getUsername());
		rabbitConnectionFactory.setPassword(config.getPassword());
		if(!StringUtils.isEmpty(config.getVirtualHost())){
			rabbitConnectionFactory.setVirtualHost(config.getVirtualHost());
		}
	}
	public synchronized void destroy() throws Exception{
		if(!isStarted.get()){
			return;
		}
		msgListenerContainer.stop();
		eventTemplate = null;
		rabbitAdmin = null;
		rabbitConnectionFactory.destroy();
	}
	@Override
	public void start() {
		if(isStarted.get()){
			return;
		}
		Set<String>mapping = msgAdapterHandler.getAllBinding();
		for(String relation:mapping){
			String[] relaArr = relation.split("\\|");
			declareBinding(relaArr[1],relaArr[0]);
		}
		initMsgListenerAdapter();
		isStarted.set(true);
	}
	private void initMsgListenerAdapter(){
		MessageListener listener = new MessageListenerAdapter(msgAdapterHandler,serializerMessageConverter);//此处注册消息接收后的处理
		msgListenerContainer = new SimpleMessageListenerContainer();
		msgListenerContainer.setConnectionFactory(rabbitConnectionFactory);
		msgListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
		msgListenerContainer.setMessageListener(listener);
		msgListenerContainer.setErrorHandler(new MessageErrorHandler());;
		msgListenerContainer.setPrefetchCount(config.getPrefetchSize());;
		msgListenerContainer.setConcurrentConsumers(config.getEventMsgProcessNum());
		msgListenerContainer.setTxSize(config.getPrefetchSize());
		msgListenerContainer.setQueues(queues.values().toArray(new Queue[queues.size()]));;
		msgListenerContainer.start();
	}
	@Override
	public EventTemplate getEopEventTemplate() {
		// TODO Auto-generated method stub
		return eventTemplate;
	}

	@Override
	public EventController add(String queueName, String exchangeName, EventProcesser eventProcesser) {
		return add(queueName,exchangeName,eventProcesser,defaultCodeFactory);
	}
	public EventController add(String queueName,String exchangeName,EventProcesser eventProcesser,CodeFactory codeFactory){
		msgAdapterHandler.add(queueName, exchangeName, eventProcesser, codeFactory);
		if(isStarted.get()){
			initMsgListenerAdapter();
		}
		return this;
	}
	@Override
	public EventController add(Map<String, String> binding, EventProcesser eventProcesser) {
		// TODO Auto-generated method stub
		return add(binding,eventProcesser,defaultCodeFactory);
	}
	private EventController add(Map<String, String> binding, EventProcesser eventProcesser,
			CodeFactory defaultCodeFactory) {
		for(Map.Entry<String, String>item:binding.entrySet()){
			msgAdapterHandler.add(item.getKey(), item.getValue(), eventProcesser, defaultCodeFactory);
		}
		return this;
	}
	protected boolean beBinded(String exchangeName,String queueName){
		return binded.contains(exchangeName+"|"+queueName);
	}
	protected synchronized void declareBinding(String exchangeName,String queueName){
		String bindRelation = exchangeName+"|"+queueName;
		if(binded.contains(bindRelation)) return;
		boolean needBinding = false;
		DirectExchange directExchange = exchanges.get(exchangeName);
		if(directExchange == null){
			directExchange = new DirectExchange(exchangeName,true,false,null);
			exchanges.put(exchangeName,directExchange);
			rabbitAdmin.declareExchange(directExchange);
			needBinding = true;
		}
		Queue queue = queues.get(queueName);
		if(queue == null){
			queue = new Queue(queueName);
			queues.put(queueName, queue);
			rabbitAdmin.declareQueue(queue);
			needBinding = true;
		}
		if(needBinding){
			Binding binding = BindingBuilder.bind(queue).to(directExchange).with(queueName);
			rabbitAdmin.declareBinding(binding);
			binded.add(bindRelation);
		}
	}

}
