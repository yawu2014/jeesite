package com.thinkgem.jeesite.spring_javaconfig.fortest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("prod")
public class DemoBeanIntegrationTests {
	@Autowired
	private TestBean testBean;
	@Test
	public void prodBeanShouldInject(){
		String expect = "form production profile";
		String actual = testBean.getContent();
//		System.out.println(expect);
//		System.out.println(actual);
		Assert.assertEquals(expect, actual);
	}
}
