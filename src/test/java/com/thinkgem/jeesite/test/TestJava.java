package com.thinkgem.jeesite.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import io.netty.util.internal.RecyclableArrayList;

public class TestJava {
	/**
	 * 死锁
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new SyncAddRunnable(2, 1)).start();
			new Thread(new SyncAddRunnable(1, 2)).start();
		}
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
}
