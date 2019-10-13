package com.liudan.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.liudan.cms.domain.Article;
import com.liudan.cms.domain.Category;
import com.liudan.cms.domain.Channel;
import com.liudan.cms.domain.Slide;
import com.liudan.cms.service.ArticleService;
import com.liudan.cms.service.CategoryService;
import com.liudan.cms.service.ChannelService;
import com.liudan.cms.service.SlideService;
import com.liudan.cms.util.PageUtil;

@Controller
public class IndexController {

//	注入栏目的属性
	@Resource
	private ChannelService channelservice;

//	注入图片的属性
	@Resource
	private SlideService slideService;

//	注入文章的属性
	@Resource
	private ArticleService articleService;
	
//	注入分类的属性
	@Resource
	private CategoryService categoryService;

	@GetMapping(value = { "", "/", "index" })
	public String index(Model m, Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "2") Integer pageSize) {
		
		//设置查询条件
		article.setStatus(1);//查询审核通过的文章
		
		// 查询栏目的所有类型
		List<Channel> channels = channelservice.selects();
		m.addAttribute("channels", channels);

		// 查询所有的图片 默认显示轮播图 即用户不点击任何栏目 --
		if (article.getChannelId() == null) {
			List<Slide> slides = slideService.selects();
			m.addAttribute("slides", slides);

			// 默认显示热门文章
			article.setHot(1);// 设置热门文章
			PageInfo<Article> info = articleService.selects(article, page, pageSize);
			String pages = PageUtil.page(page, info.getPages(), "/", pageSize);
			m.addAttribute("list", info.getList());
			m.addAttribute("pages", pages);
		}
			//查询出栏目下的id   用户点击栏目  即查询出栏目的分类
		if(article.getChannelId() != null) {
			List<Category> category = categoryService.selects(article.getChannelId());
			m.addAttribute("category", category);
			PageInfo<Article> info2 = articleService.selects(article, page, pageSize);
			String pages = PageUtil.page(page, info2.getPages(), "/", pageSize);
			m.addAttribute("articles", info2.getList());
			m.addAttribute("pages", pages);
		}
		
		
		// 设置最新文章(按照文章的发布时间倒序排序，支取10篇)
		PageInfo<Article> info2 = articleService.selects(new Article(), 1, 10);
		m.addAttribute("list1", info2.getList());

		// 封装查询条件
		m.addAttribute("article", article);
		return "index/index";
	}
}
