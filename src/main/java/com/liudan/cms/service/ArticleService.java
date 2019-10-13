package com.liudan.cms.service;

import com.github.pagehelper.PageInfo;
import com.liudan.cms.domain.Article;

public interface ArticleService {

//	文章列表的方法
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeyWithBLOBs(Article record);

	int updateByPrimaryKeySelective(Article article);
	
	//上一篇
    Article selectPreByDate(Article article);
    
  //下一篇
    Article selectSufByDate(Article article);
//	修改文章的分类
	boolean updateByid(Integer id);
}
