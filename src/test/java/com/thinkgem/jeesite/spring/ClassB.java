package com.thinkgem.jeesite.spring;

public class ClassB extends PBean implements InterfaceA,InterfaceB,InterfaceC{
	public ClassB(){
		BeanA a = new BeanA();
//		a.setInterface(this);此处有问题不能设置
	}
	//父类中可以和接口的类名一致,且父类中实现的方法名可以和接口中的一致,并充当接口的方法实现
	//可以同时实现这两个接口名重复接口
	@Override
	public void onInterfaceA() {
		
	}
}
