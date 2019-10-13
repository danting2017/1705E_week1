package com.liudan.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liudan.cms.dao.CategoryMapper;
import com.liudan.cms.domain.Category;
import com.liudan.cms.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryMapper mapper;
	
	@Override
	public List<Category> selects(Integer channelId) {
		// TODO Auto-generated method stub
		return mapper.selects(channelId);
	}

}
