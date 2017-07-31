package com.thinkgem.jeesite.spring.bean;

public class MyBean {
	private String name;
	private int id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void testFinally(){
		try{
			System.out.println("testFinally");//return 之前先执行finally
			return ;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("finally");
		}
	}
}
