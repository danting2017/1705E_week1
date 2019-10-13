package com.liudan.cms.dao;

import java.util.List;


import com.liudan.cms.domain.Article;

public interface ArticleMapper {
//	文章列表的方法
	List<Article> selects(Article article);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    
  //上一篇
    Article selectPreByDate(Article article);
    
  //下一篇
    Article selectSufByDate(Article article);
//	修改文章的分类
	boolean updateByid(Integer id);
}