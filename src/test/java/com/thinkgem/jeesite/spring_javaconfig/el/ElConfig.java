package com.thinkgem.jeesite.spring_javaconfig.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan("com.thinkgem.jeesite.spring_javaconfig.el")
@PropertySource("classpath:test.properties")
public class ElConfig {
	@Value("I Love You!")
	private String normal;
	@Value("#{systemProperties['os.name']}")
	private String osName;
	@Value("#{ T(java.lang.Math).random()*100.0 }")
	private double randomNaumber;
	@Value("#{ demoService.another}")
	private String fromAnother;
	@Value("classpath:test.txt")
	private Resource testFile;
	@Value("http://www.baidu.com")
	private Resource testUrl;
	@Value("${book.name}")
	private String bookName;
	@Autowired
	private Environment environment;
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigure(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	public void outputResource(){
		try{
			System.out.println(normal);
			System.out.println(osName);
			System.out.println(randomNaumber);
			System.out.print(fromAnother);
			System.out.println(IOUtils.toString(testFile.getInputStream()));
			System.out.println(IOUtils.toString(testUrl.getInputStream()));
			System.out.println(bookName);
			System.out.println(environment.getProperty("book.author"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
