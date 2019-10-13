package com.liudan.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liudan.cms.domain.User;

public interface UserMapper {
	
//	写list页面的方法
	
	List<User> selects(@Param("username")String username);
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

//	根据姓名查询出一个对象
	User selectByname(@Param("username")String username);
}