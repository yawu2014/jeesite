package com.thinkgem.jeesite.spring_javaconfig.conditional;

public class LinuxListService implements ListService {

	@Override
	public String showListCmd() {
		return "ls";
	}

}
