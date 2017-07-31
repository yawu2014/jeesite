package com.thinkgem.jeesite.concurrent;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SetTest {
	public static void main(String[] args) {
		ConcurrentHashMap<String,Boolean> conHashMap = new ConcurrentHashMap<String, Boolean>(16);
		conHashMap.put("1", Boolean.valueOf(true));
		//生成了内部的SetFromMap对象,对象的赋值指向问题,地址复制但引用的还是一个对象
		Set<String> singletonsCurrentlyInCreation =
				Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>(16));
		singletonsCurrentlyInCreation.add("show");
		singletonsCurrentlyInCreation.add("show2");
		for(String value : singletonsCurrentlyInCreation){
			System.out.println(value);
			if(singletonsCurrentlyInCreation.contains("show")){
				singletonsCurrentlyInCreation.remove("show");
			}
		}
		System.out.println("--------------------------"+singletonsCurrentlyInCreation.contains("show"));
		for(String value : singletonsCurrentlyInCreation){
			System.out.println(value);
		}
//		singletonsCurrentlyInCreation
	}
}
