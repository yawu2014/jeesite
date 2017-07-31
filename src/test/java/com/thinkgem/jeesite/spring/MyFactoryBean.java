package com.thinkgem.jeesite.spring;

import org.springframework.beans.factory.FactoryBean;

import com.thinkgem.jeesite.spring.bean.MyBean;
/**
 * 
 * 测试FactoryBean
 * @author liuyujian
 * @Date 2017年7月17日 下午1:33:11
 *
 */
public class MyFactoryBean implements FactoryBean<MyBean> {

	@Override
	public MyBean getObject() throws Exception {
		MyBean classB = new MyBean();
		return classB;
	}

	@Override
	public Class<?> getObjectType() {
		return MyBean.class.getClass();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
