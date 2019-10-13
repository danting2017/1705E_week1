package com.liudan.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.liudan.cms.domain.User;
import com.liudan.cms.service.UserService;
import com.liudan.cms.util.PageUtil;

@RequestMapping("user")//代表的是限定   说明我只跳入到user里面
@Controller
public class UserController {
	
	@Resource
	private UserService userservice;
	
	@RequestMapping("selects")
	public String index(Model m,@RequestParam(defaultValue = "")String username,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "2")Integer pageSize) {
		PageInfo<User> info = userservice.selects(username, page, pageSize);
		String pages = PageUtil.page(page, info.getPages(), "/user/selects?username="+username, pageSize);
		m.addAttribute("list", info.getList());
		m.addAttribute("username", username);
		m.addAttribute("pages", pages);
		
		return "admin/users";
	}
	
	@ResponseBody
	@PostMapping("update")
	public boolean update(User user) {
		return userservice.updateByPrimaryKeySelective(user)>0;
	}
}
