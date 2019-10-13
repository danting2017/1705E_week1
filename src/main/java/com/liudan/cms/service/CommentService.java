package com.liudan.cms.service;

import com.github.pagehelper.PageInfo;
import com.liudan.cms.domain.Comment;
/**
 * 
  * 文章的评论
 *
 */
public interface CommentService {

//	查询所有的文章
	PageInfo<Comment> selects(Integer articleId,Integer page,Integer pageSize);
	
//	添加文章
	int insert(Comment comment);
	
}
