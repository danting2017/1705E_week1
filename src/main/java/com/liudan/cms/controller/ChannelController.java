package com.liudan.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liudan.cms.domain.Channel;
import com.liudan.cms.service.ChannelService;

@RequestMapping("channel")
@Controller
public class ChannelController {

	@Resource
	private ChannelService service;
//	返回所有的栏目
	@ResponseBody
	@RequestMapping("selects")
	public List<Channel> selects(){
		List<Channel> list = service.selects();
		return list;
	}
}
