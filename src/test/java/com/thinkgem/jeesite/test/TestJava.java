package com.thinkgem.jeesite.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.spring.bean.MyBean;

import io.netty.util.internal.RecyclableArrayList;

public class TestJava {
	/**
	 * 死锁
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
		for(ThreadInfo threadInfo:threadInfos){
			System.out.println(threadInfo.getThreadId()+":"+threadInfo.getThreadName());
		}
		/*for (int i = 0; i < 100; i++) {
			new Thread(new SyncAddRunnable(2, 1)).start();
			new Thread(new SyncAddRunnable(1, 2)).start();
		}*/
//		RecyclableArrayList recyclableArrayList;
	}
	static class SyncAddRunnable implements Runnable{
		int a,b;
		public SyncAddRunnable(int a,int b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public void run() {
			synchronized (Integer.valueOf(a)){
				System.out.println(Thread.currentThread().getName()+" a:"+a);
				synchronized(Integer.valueOf(b)){
					System.out.println(Thread.currentThread().getName()+" result:"+a+b);
				}
			}
			
		}
		
	}
	
	@Test
	public void testInteger(){
		System.out.println(Integer.toString(30));
		List<Integer> lists = Lists.newArrayList(30,127,256);
		System.out.println(lists);
		lists.remove(Integer.valueOf(30));
		System.out.println(lists);
		lists.remove(Integer.valueOf(Integer.valueOf(256)));
		System.out.println(lists);
	}
	@Test
	public void testReadFile(){
		File file = new File("E:\\github\\jeesite\\src\\test\\java\\com\\thinkgem\\jeesite\\test\\file.txt");
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
			String strTmp = "";
			int i=1;
			while((strTmp = reader.readLine()) != null){
				String[] strArr = strTmp.split(",");
				/*System.out.println("ContactBean bean"+i+" = new ContactBean();");
				System.out.println("bean"+i+".setUid("+strArr[0]+");");
				System.out.println("bean"+i+".setName(\""+strArr[1]+"\");");
				System.out.println("bean"+i+".setCode(\""+strArr[2]+"\");");
				System.out.println("lists.add(bean"+i+");");*/
				System.out.println("userHashMap.put(\""+strArr[2]+"\",\""+strArr[0]+"\");");
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Test
	public void testDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println(date.getTime());
		
		Date date2 = new Date(1508825830163l);
		System.out.println(sdf.format(date2));
	}
	/**
	 * 
	 * <p>
	 * 经过Arrays.sort之后,原数组会变成有序数组
	 * </p>
	 * 
	 * @author liuyujian
	 * @date 2017年9月22日 下午4:37:52
	 */
	@Test
	public void testArrays() {
		int[] a = new int[] {3,2,4,1,6};
		for(int i:a) {
			System.out.print(i);
		}
		System.out.println("----------排序后的数组:--------");
		Arrays.sort(a);
		for(int i:a) {
			System.out.print(i);
		}
		System.out.println("----------查找元素的数组:--------");
		System.out.println(Arrays.binarySearch(a, 2));//若查找的元素不存在,返回插入该查找元素的插入位置取负值
		for(int i:a) {
			System.out.print(i);
		}
	}
	
	@Test
	public void testIdentity() {
		int[] value = new int[] {75,64,11,8,3,2};
		List<String> resultList2 = Lists.newArrayList();
		List<String> resultList3 = Lists.newArrayList();
		Map<Integer,String> resultMap2 = new TreeMap<Integer,String>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				int value = o1.intValue() - o2.intValue();
				if(value > 0) {
					return -1;
				}else if(value < 0) {
					return 1;
				}
				return 0;
			}
		});
		Map<Integer,String> resultMap3 = new TreeMap<Integer,String>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				int value = o1.intValue() - o2.intValue();
				if(value > 0) {
					return -1;
				}else if(value < 0) {
					return 1;
				}
				return 0;
			}
		});
		for(int i=0;i<value.length;i++) {
			for(int j=i;j<value.length;j++) {
//				System.out.printf("[%s,%s]:%s\t",(i+1),(j+1),(value[i]+value[j]));
				for(int k=j;k<value.length;k++) {
					resultMap3.put((value[i]+value[j]+value[k]), (i+1)+":"+(j+1)+":"+(k+1));
				}
//				System.out.print((value[i]+value[j])+"\t");
				resultMap2.put((value[i]+value[j]), (i+1)+":"+(j+1));
			}
			System.out.println("");
		}
		resultMap2.forEach((key,v)->{
			System.out.println(v+":"+key);
			resultList2.add(v+":"+key);
		});
		/*resultMap3.forEach((key,v)->{
			System.out.println(v+":"+key);
			resultList3.add(v+":"+key);
		});*/
		/*System.out.println("--------此处是分割线-----------");
		resultList2.stream().filter(str->{
			Iterator<String> strIt = Splitter.on(":").trimResults().split(str).iterator();
			List<String> res = Lists.newArrayList(strIt);
			if(checkValue(res.get(0))&& checkValue(res.get(1))) {
				return true;
			}
			return false;
		}).forEach(str->{
			System.out.println(str);
		});
		System.out.println("----------华丽的分割线------------------");
		resultList3.stream().filter(str->{
			Iterator<String> strIt = Splitter.on(":").trimResults().split(str).iterator();
			List<String> res = Lists.newArrayList(strIt);
			if(checkValue(res.get(0))&& checkValue(res.get(1))&&checkValue(res.get(2))) {
				return true;
			}
			return false;
		}).forEach(str->{
			System.out.println(str);
		});*/
	}
	
	private boolean checkValue(String str) {
		int[] bins = new int[] {1,2,3,4};
		int v = Integer.valueOf(str);
		return Arrays.binarySearch(bins, v) > -1;
	}
	@Test
	public void testMap() {
		List<MyBean> beans = Lists.newArrayList();
		for(int i=0;i<3;i++) {
			MyBean bean = new MyBean();
			bean.setId(i);
			bean.setName("hello"+i);
			beans.add(bean);
		}
		//stream中修改的对象状态可以保存
		beans.stream().filter(b->{return b.getId()>1;}).forEach(b->{b.setName("World");});
		beans.forEach(b->{
			System.out.println(b.getName());
		});
	}
	@Test
	public void testBit() {
		int mask = 1<<3;
		System.out.println(1<<2);
		System.out.println(9 & mask);
	}


}
