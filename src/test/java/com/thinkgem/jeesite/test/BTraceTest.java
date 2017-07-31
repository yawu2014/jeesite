package com.thinkgem.jeesite.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.alibaba.druid.util.HttpClientUtils;

import sun.net.www.http.HttpClient;

public class BTraceTest {
	public int add(int a,int b){
		return a+b;
	}
	public static void main(String[] args) throws IOException {
		BTraceTest test = new BTraceTest();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<10;i++){
			reader.readLine();
			int a = (int)Math.round(Math.random()*1000);
			int b = (int)Math.round(Math.random()*1000);
			System.out.println(test.add(a, b));
		}
	}
	
	@Test
	public void testString(){
		String str = "34,33";
		System.out.println(str.indexOf(","));
		System.out.print(str.substring(str.indexOf(",")));
	}
	@Test
	public void testData(){
		Date date = new Date(1499073545604l);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));
		Date dataNow = new Date();
		System.out.println(dataNow.getTime());
	}
	
}
