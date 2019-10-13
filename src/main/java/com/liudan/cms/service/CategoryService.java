package com.liudan.cms.service;

import java.util.List;

import com.liudan.cms.domain.Category;

public interface CategoryService {

//	根据栏目的id值查询出分类的对象
	List<Category> selects(Integer channelId);
}
