package com.liudan.cms.dao;

import java.util.List;

import com.liudan.cms.domain.Category;

public interface CategoryMapper {
	
//	根据栏目的id值查询出分类的对象
	List<Category> selects(Integer channelId);
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}