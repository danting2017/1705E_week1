package com.liudan.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("my")
@Controller
public class MyController {

	@GetMapping(value = {"","/","my"})
	public String index() {
		
		return "my/index";
	}
}
