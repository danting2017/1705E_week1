package com.liudan.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liudan.cms.domain.Category;
import com.liudan.cms.service.CategoryService;

@RequestMapping("category")
@Controller
public class CategoryController {

	@Resource
	private CategoryService service;
	
	
//	返回栏目下所有的分类
	@ResponseBody
	@GetMapping("selects")
	public List<Category> selects(Integer channelId) {
		System.err.println(channelId);
		List<Category> list = service.selects(channelId);
		
		return list;
	}
}
