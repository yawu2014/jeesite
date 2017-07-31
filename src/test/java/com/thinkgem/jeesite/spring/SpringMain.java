package com.thinkgem.jeesite.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import com.thinkgem.jeesite.spring.bean.MyBean;

public class SpringMain implements InterfaceA,InterfaceB {

	public static void main(String[] args) {
		ClassPathResource resource = new ClassPathResource("spring-test.xml");
		Assert.notNull(resource);
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(resource);
		
		MyBean myBean = factory.getBean(MyBean.class);
		myBean.testFinally();
		MyBean myBean2 = (MyBean) factory.getBean("myFactoryBean");
		System.out.println("myBean2:"+myBean2.getClass().getName());
		
		MyFactoryBean bean = (MyFactoryBean)factory.getBean("&myFactoryBean",MyFactoryBean.class);
		System.out.println(bean.getClass().getCanonicalName());
		
		System.out.println(factory.containsBean("&myFactoryBean"));
		System.out.println(factory.isSingleton("myBean"));
		
		System.out.println(factory.containsBean("myBeanT"));
		System.out.println("successful");
	}

	@Override
	public void onInterfaceA() {
		// TODO Auto-generated method stub
		
	}
}
