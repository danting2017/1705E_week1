package com.liudan.cms.dao;

import java.util.List;

import com.liudan.cms.domain.Slide;

public interface SlideMapper {
	
//	查询所有的图片
	List<Slide> selects();
	
    int deleteByPrimaryKey(Integer id);

    int insert(Slide record);

    int insertSelective(Slide record);

    Slide selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Slide record);

    int updateByPrimaryKey(Slide record);
}