/**
 * 
 */
package com.thinkgem.jeesite.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年8月16日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class testString {
	@Test
	public void function1() {
		String str = "51kg ~ 66kg";
		// 4.0～6.0mmol/L
		Iterable<String> results = Splitter.on("~").omitEmptyStrings().trimResults().split(str);;
		for(String res:results) {
			System.out.println(CharMatcher.DIGIT.retainFrom(res));
		}
		String str2 ="收缩压 ＜140 舒张压 ＜90";
		Iterable<String> results2 = Splitter.on("＜").omitEmptyStrings().trimResults().split(str2);;
		for(String res:results2) {
			System.out.println(CharMatcher.DIGIT.retainFrom(res));
		}
	}
	@Test
	public void function2() {
		List<Integer> res = Lists.newArrayList(1,2,3,4,5,6,7);
		for(Integer i:res) {
			System.out.println(i+"to"+((i+5)%7+1));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH)+1;
		if(month < 4) {
			cal.set(Calendar.MONTH,0);
		}else if(month < 7) {
			cal.set(Calendar.MONTH, 3);
		}else if(month < 10) {
			cal.set(Calendar.MONTH, 6);
		}else if(month < 13) {
			cal.set(Calendar.MONTH,9);
		}
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		System.out.println(sdf.format(new Date(cal.getTimeInMillis())));
		
		List<String> list = Lists.newArrayListWithCapacity(8);
	}
	@Test
	public void function3() {
		List<String> list = Lists.newArrayListWithCapacity(8);
		
//		list.set(1, "sss");
		List<String> list2 = Lists.newArrayListWithExpectedSize(7);
//		list2.set(3, "xxx");
		
		List<String> list3 = Lists.newArrayList("0","0","0","0","0","0","0","0");;
		list3.set(4, "xxx");
	}
	@Test
	public void function4() {
		String dig="44";
		System.out.println(dig.substring(0, 1)+"."+dig.substring(1));
		Map<String,String> map = new HashMap<String,String>();
	}
}
