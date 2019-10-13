package com.liudan.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liudan.cms.dao.ChannelMapper;
import com.liudan.cms.domain.Channel;
import com.liudan.cms.service.ChannelService;
@Service
public class ChannelServiceimpl implements ChannelService {

	@Resource
	private ChannelMapper mapper;
	
	@Override
	public List<Channel> selects() {
		
		return mapper.selects();
	}

}
