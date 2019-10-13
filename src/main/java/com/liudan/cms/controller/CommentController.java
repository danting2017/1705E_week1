package com.liudan.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.liudan.cms.service.CommentService;

@Controller
public class CommentController {

	@Resource
	private CommentService service;
	
	
}
