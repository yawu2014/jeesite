package com.thinkgem.jeesite.dynamicproxy;

public class RealSubject implements Subject {

	@Override
	public void doSomething() {
		System.out.println(" call doSomethind ");
	}

}
