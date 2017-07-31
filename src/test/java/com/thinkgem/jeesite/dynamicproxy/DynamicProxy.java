package com.thinkgem.jeesite.dynamicproxy;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

import sun.misc.ProxyGenerator;

public class DynamicProxy {
	/**
	 * 代理方式是通过动态创建一个继承了Proxy实现了相应接口类的匿名类,并赋值给变量,变量在执行接口方法的时候被代理到Handler的invoke方法中
	 * @param args
	 */
	public static void main(String[] args) {
		RealSubject real = new RealSubject();
		Subject proxySubject = (Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, new ProxyHandler(real));
		proxySubject.doSomething();
		createProxyClassFile();
	}
	
	public static void createProxyClassFile(){
		String name = "ProxySubject";
		byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{Subject.class});
		try{
			FileOutputStream out = new FileOutputStream(name+".class");
			out.write(data);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
