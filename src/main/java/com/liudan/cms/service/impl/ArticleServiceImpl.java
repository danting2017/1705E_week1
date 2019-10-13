package com.liudan.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liudan.cms.dao.ArticleMapper;
import com.liudan.cms.domain.Article;
import com.liudan.cms.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleMapper articlemapper;
	
	@Override
	public PageInfo<Article> selects(Article article, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Article> selects = articlemapper.selects(article);
		return new PageInfo<Article>(selects);
	}

	@Override
	public int insertSelective(Article record) {
		// TODO Auto-generated method stub
		return articlemapper.insertSelective(record);
	}

	@Override
	public Article selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articlemapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Article record) {
		// TODO Auto-generated method stub
		return articlemapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Article article) {
		// TODO Auto-generated method stub
		return articlemapper.updateByPrimaryKeySelective(article);
	}

//	上一篇
	@Override
	public Article selectPreByDate(Article article) {
		
		return articlemapper.selectPreByDate(article);
	}
//	下一篇
	@Override
	public Article selectSufByDate(Article article) {
		
		return articlemapper.selectSufByDate(article);
	}

	@Override
	public boolean updateByid(Integer id) {
		
		return articlemapper.updateByid(id);
	}

}
