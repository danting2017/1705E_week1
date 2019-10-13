package com.liudan.cms.service;

import com.github.pagehelper.PageInfo;
import com.liudan.cms.domain.User;
import com.liudan.cms.vo.UserVO;

public interface UserService {

//编写列表展示的方法
	PageInfo<User> selects(String username, Integer page, Integer pageSize);

//动态sql插入文件
	int insertSelective(UserVO uservo);

//根据主键查询文件
	User selectByPrimaryKey(Integer id);

//动态修改文件
	int updateByPrimaryKeySelective(User record);

//登录
	User login(User user);

}
