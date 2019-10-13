package com.liudan.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liudan.cms.dao.UserMapper;
import com.liudan.cms.domain.User;
import com.liudan.cms.service.UserService;
import com.liudan.cms.util.CMSException;
import com.liudan.cms.util.Md5Util;
import com.liudan.cms.vo.UserVO;
import com.liudan.common.utils.StringUtil;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper usermapper;

	@Override
	public int insertSelective(UserVO uservo) {
//		后台校验
		// 1 两次密码是否一致
		if (!uservo.getPassword().equals(uservo.getRepassword()))
			throw new CMSException("两次密码不一致");
		// 2 用户名是否已经存在
		User user = usermapper.selectByname(uservo.getUsername());
		if (null != user) {
			throw new CMSException("用户名已经存在，请更换用户名");
		}
		//3、对密码进行加密
		String newpassword = Md5Util.md5Encoding(uservo.getPassword());
		//存在uservo中
		uservo.setPassword(newpassword);
		//4、注册  用户的初始默认值
		uservo.setLocked(0);//默认不禁用
		uservo.setCreated(new Date());//注册时间
		uservo.setUpdated(new Date());//修改时间
		uservo.setNickname(uservo.getUsername());//设置昵称
		uservo.setRole(0);//默认设置为普通用户
		
		return usermapper.insertSelective(uservo);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {

		return usermapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {

		return usermapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public PageInfo<User> selects(String username, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<User> selects = usermapper.selects(username);
		return new PageInfo<User>(selects);
	}

	@Override
	public User login(User user) {
//		进行后台验证
		//1.当传过来一个空值时
		if(null==user)
			throw new CMSException("用户名不可以为空");
		//2.当username 为空时
		if(!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不可以为空");
		//3.当 password 为空时
		if(!StringUtil.hasText(user.getPassword()))
			throw new CMSException("密码不可以为空");
		//4、判断用户名是否存在
		User user2 = usermapper.selectByname(user.getUsername());
		if(null==user2)
			throw new CMSException("用户名不存在");
		//5、判断密码
		if(!Md5Util.md5Encoding(user.getPassword()).equals(user2.getPassword()))
			throw new CMSException("密码不正确");
		//6.当账户被停用时
		if(user2.getLocked()==1)
		 throw new CMSException("登录失败:用户账户被停用");
		 
		return user2;
	}

}
