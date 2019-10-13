package com.liudan.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liudan.cms.dao.SlideMapper;
import com.liudan.cms.domain.Slide;
import com.liudan.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService {

	@Resource
	private SlideMapper mapper;
	
	@Override
	public List<Slide> selects() {
		// TODO Auto-generated method stub
		return mapper.selects();
	}

}
