package com.thinkgem.jeesite.spring_javaconfig.conditional;

public class WindowsListService implements ListService {

	@Override
	public String showListCmd() {
		return "dir";
	}

}
