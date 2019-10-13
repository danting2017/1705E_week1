package com.liudan.cms.dao;

import java.util.List;

import com.liudan.cms.domain.Channel;

public interface ChannelMapper {
	
//	查询出所有的栏目
	List<Channel> selects();
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}