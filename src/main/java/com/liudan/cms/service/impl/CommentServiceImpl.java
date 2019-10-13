package com.liudan.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liudan.cms.dao.CommentMapper;
import com.liudan.cms.domain.Comment;
import com.liudan.cms.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentMapper mapper;
	

	@Override
	public int insert(Comment comment) {
		
		return mapper.insert(comment);
	}


	@Override
	public PageInfo<Comment> selects(Integer articleId,Integer page,Integer pageSize) {
		
		PageHelper.startPage(page, pageSize);
		List<Comment> list = mapper.selects(articleId);
		
		return new PageInfo<Comment>(list);
	}

}
