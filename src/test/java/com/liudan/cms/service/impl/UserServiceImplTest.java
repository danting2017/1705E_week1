package com.liudan.cms.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.pagehelper.PageInfo;
import com.liudan.cms.domain.User;
import com.liudan.cms.service.UserService;


public class UserServiceImplTest extends JunitParent{

	@Resource
	private UserService userservice;
	@Test
	public void testInsertSelective() {
		
	}

	@Test
	public void testSelectByPrimaryKey() {
		
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		
	}

	@Test
	public void testSelects() {
		PageInfo<User> info = userservice.selects(null, 1, 2);
		System.out.println(info.getList());
	}

}
