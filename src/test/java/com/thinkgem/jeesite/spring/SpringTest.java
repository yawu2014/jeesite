package com.thinkgem.jeesite.spring;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.spring.bean.PhysicalExaminationIndexParam;
import com.thinkgem.jeesite.spring.bean.ResponseParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test.xml")
public class SpringTest {
	@Test
	public void testGetObject(){
		
	}
	
	public RestTemplate restTemplate = new RestTemplate();
	@Test
	public void testRestTemplate(){
		String id = "090320010001";
		ResponseParam param = restTemplate.getForObject("http://218.241.151.250/p/physical_examination_info/get_single_report?orgId=1&peId="+id, ResponseParam.class);
		Map<String,List<PhysicalExaminationIndexParam>> result = (Map<String, List<PhysicalExaminationIndexParam>>) param.getData();
		System.out.println(JSON.toJSONString(result));
	}
}
