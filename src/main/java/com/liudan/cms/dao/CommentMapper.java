package com.liudan.cms.dao;

import java.util.List;

import com.liudan.cms.domain.Comment;

public interface CommentMapper {

//	查询所有的文章
	List<Comment> selects(Integer articleId);
	
//	添加文章
	int insert(Comment comment);
}
