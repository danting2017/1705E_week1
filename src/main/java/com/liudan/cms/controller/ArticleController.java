package com.liudan.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.liudan.cms.domain.Article;
import com.liudan.cms.domain.Comment;
import com.liudan.cms.domain.User;
import com.liudan.cms.service.ArticleService;
import com.liudan.cms.service.CommentService;
import com.liudan.cms.util.PageUtil;

@RequestMapping("article")
@Controller
public class ArticleController {

//	注入文章的service
	@Resource
	private ArticleService service;
	
//	注入评论的service
	@Resource
	private CommentService service1;
	
	@Value("${filepath}")
	private String path;//文件上传的路径
	
	
	
//	走评论的方法
	@ResponseBody
	@PostMapping("comment")
	public  boolean comment(Comment comment,HttpServletRequest request) {
		
		comment.setCreated(new Date());//评论时间	
		//从session获取评论人
		HttpSession session = request.getSession(false);
		if(null ==session)
			return false;
		User user = (User) session.getAttribute("user");
		if(null==user)
			return false;
		comment.setUser(user);//评论人
		return service1.insert(comment)>0;
	}
	
	@RequestMapping("list")
	public boolean list(Model m,Article article,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "5")Integer pageSize) {
		PageInfo<Comment> info = service1.selects(article.getId(), page, pageSize);
		String page2 = PageUtil.page(page, info.getPages(), "/article/list", pageSize);
		
		m.addAttribute("list", info.getList());
		m.addAttribute("page2", page2);
		return true;
	}
	/**
	 * 用户查看自己的文章
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("selectByUser")
	public String selectByUser(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize,@RequestParam(required = true)Integer id,Model model) {
		
		Article article = service.selectByPrimaryKey(id);
		
		model.addAttribute("article", article);
		
		//文章评论
		PageInfo<Comment> info = service1.selects(article.getId(),page, pageSize);
		String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);
		
		model.addAttribute("comment", info.getList());
		model.addAttribute("pages", pages);
		
		//设置最新的评论
		PageInfo<Comment> info2 = service1.selects(article.getId(), 1, 10);
		model.addAttribute("comment1", info2.getList());
		return "/my/article";
	}
	
	
	//注册用户查看自己的文章
	@RequestMapping("selectsByuser")
	public String selectsByuser(Model m,Article article,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "2")Integer pageSize,HttpServletRequest request) {
		
		//个人只能查看自己的发布的文章
		HttpSession session = request.getSession(false);
		if(session==null) {//session过期.重新登录
			return "redirect:/passport/login";
		}
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());//文章作者
		
		//动态拼接路径
		String url = "/article/selectsByuser";
		if(article.getTitle()!=null) {
			url += "?title="+article.getTitle();
		}
		
		PageInfo<Article> info = service.selects(article, page, pageSize);
		String pages = PageUtil.page(page, info.getPages(), url, pageSize);
		m.addAttribute("list", info.getList());
		m.addAttribute("pages", pages);
		m.addAttribute("article",article);
		
		return "my/articles";
	}
	
	
	@GetMapping("publish")
	public String publish() {
		
		return "my/publish";
	}
	
	
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(Article article,MultipartFile file,HttpServletRequest request) {
//		String path = "d:/pic/";
		//1、文件标题图片上传
		if(!file.isEmpty()) {
			//获取原始文件名称
			String oldfile = file.getOriginalFilename();
			//为了防止文件名重复，使用UUID处理文件名称
			String newfile = UUID.randomUUID()+ oldfile.substring(oldfile.lastIndexOf("."));
			
			File file2 = new File(path+newfile);
			
			try {
				file.transferTo(file2);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//2、保存文章内容
			//文章的默认属性
			article.setStatus(0);//文章默认为待审核状态
			article.setHot(0);//默认非热点
			article.setDeleted(0);//默认非删除
			article.setHits(0);//点击量
			article.setCreated(new Date());
			article.setUpdated(new Date());
			//设置发布人
			HttpSession session = request.getSession(false);
			if(session==null) {
				return false;
			}
			User user = (User) session.getAttribute("user");
			article.setUserId(user.getId());//文章作者
			article.setPicture(newfile);
			int i = service.insertSelective(article);
			return i>0;
			
		}
		return false;
		
	}
	
	
	//管理员查看文章
	@RequestMapping("selectsByAdmin")
	public String selects(Model m,Article article,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "2")Integer pageSize) {
		
		PageInfo<Article> info = service.selects(article, page, pageSize);
		String pages = PageUtil.page(page, info.getPages(), "/article/selectsByAdmin?status="+article.getStatus(), pageSize);
		
		m.addAttribute("list", info.getList());
		m.addAttribute("pages", pages);
		m.addAttribute("article",article);
		
		return "/admin/article";
	}
	
	
//	查看文章详情
	@GetMapping(value = "selectByAdmin" )
	public String selectByAdmin(@RequestParam(required = true)Integer id,Model model) {
		
		Article article = service.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "admin/article1";
	}
	
	@ResponseBody
	@PostMapping("update")
	public  boolean update(Article article) {
		return service.updateByPrimaryKeySelective(article)>0;
	}
	
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 去修改
	 * @return
	 * @return: String
	 */
	@GetMapping("update")
	public String update(Model model,Integer id) {
		Article article = service.selectByPrimaryKey(id);
		
		 model.addAttribute("article", article);
		 return "/my/publishupdate";
	}
	

	//修改文章
	@ResponseBody
	@PostMapping("publishupdate")
	public boolean publishupdate(Article article, MultipartFile file,HttpServletRequest request) {
		//String path="d:/pic/";
		//1文件标题图片上传
		if(!file.isEmpty()) {
			//获取原始的文件名称
		 String oldFileName = file.getOriginalFilename();	
		 
		 //为了防止文件名称重复,使用UUID 处理文件名称
	  String newFilename=UUID.randomUUID()+ oldFileName.substring(oldFileName.lastIndexOf("."));
			
			File file2 = new File(path + newFilename);
			try {
				file.transferTo(file2);
				article.setPicture(newFilename);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//2 保存文章内容
		article.setUpdated(new Date());
		//发布人
		HttpSession session = request.getSession(false);
		if(session==null)
			return false;
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());//文章作者
		return service.updateByPrimaryKeySelective(article)>0;
	}
	
	
	//检查前一篇是否为空/
	@ResponseBody
	@GetMapping("checkPre")
	public boolean checkPre(Article article) {
		Article a = service.selectPreByDate(article);
       return a!=null;

	}
	
	//检查后一篇是否为空/
		@ResponseBody
		@GetMapping("checkSuf")
		public boolean checkSuf(Article article) {
			Article a = service.selectSufByDate(article);
	       return a!=null;

		}

//	上一篇
	@RequestMapping(value = "selectByPre")
	public String selectByPre(Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize, Model model) {

		article.setStatus(1);//这是审核过的
		article.setDeleted(0);//这是不可以删除的
		Article a = service.selectPreByDate(article);
		model.addAttribute("article", a);// 封装文章对象

		// 文章评论
		PageInfo<Comment> info = service1.selects(article.getId(), page, pageSize);

		String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);

		model.addAttribute("comments", info.getList());// 封装评论
		model.addAttribute("pages", pages);// 评论的分页
		
		return "my/article";
	}

//	下一篇
	@RequestMapping(value = "selectBySuf")
	public String selectBySuf(Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize, Model model) {

		article.setStatus(1);//这是审核过的
		article.setDeleted(0);//这是不可以删除的		
		Article a = service.selectSufByDate(article);
		model.addAttribute("article", a);// 封装文章对象

		// 文章评论
		PageInfo<Comment> info = service1.selects(article.getId(), page, pageSize);

		String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);

		model.addAttribute("comments", info.getList());// 封装评论
		model.addAttribute("pages", pages);// 评论的分页

		return "my/article";
	}
	
//	修改文章的分类和栏目
	@RequestMapping("updateByid")
	public String updateByid(Integer id) {
		System.out.println(id);
		
		return "my/banji";
	}
	
	
}
