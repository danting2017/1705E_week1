package com.liudan.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liudan.cms.domain.User;
import com.liudan.cms.service.UserService;
import com.liudan.cms.util.CMSContant;
import com.liudan.cms.util.CMSException;
import com.liudan.cms.vo.UserVO;

@RequestMapping("passport")
@Controller
public class PassportController {

	@Resource
	private UserService service;

//	注册
	@PostMapping("reg")
	public String reg(Model m, @Valid UserVO uservo, BindingResult result, RedirectAttributes redirect) {

		try {
			// 校验是否有不合法的信息，如果有就回到注册页面，进行提示
			if (result.hasErrors()) {

				return "passport/reg";
			}
			service.insertSelective(uservo);
//			注册成功，把用户名携带到登录页面
			redirect.addFlashAttribute("username", uservo.getUsername());
			return "redirect:/passport/login";// 注册成功，跳转到登录页面
			
		} catch (CMSException e) {// 捕获自定义异常
			e.printStackTrace();// 打印到输出台上
			m.addAttribute("error", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("error", "系统异常，请于管理员联系");
		}
		return "passport/reg";// 注册失败，回到注册页面
	}

//	去注册
	@GetMapping("reg")
	public String reg() {

		return "passport/reg";
	}

//	去登录
	@GetMapping("login")
	public String login() {

		return "passport/login";
	}

//	登录的方法
	@PostMapping("login")
	public String login(User user, HttpServletRequest request) {

		try {

			User user2 = service.login(user);
			// 登录成功,存在session域对象中

//			根据角色来进入不同的页面
			if (CMSContant.USER_ROLE==user2.getRole()) {
				
				request.getSession().setAttribute("user", user2);
				
				return "redirect:/my";
			}
			request.getSession().setAttribute("admin", user2);
			return "redirect:/admin";// 管理员
		} catch (CMSException e) {// 捕获自定义异常
			e.printStackTrace();// 打印给程序员看的错误
			request.setAttribute("error", e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "系统异常");
		}

		return "passport/login";// 登录失败
	}

	// 退出的方法
	@GetMapping("logout")
	public String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		return "passport/login";
	}
	
}
