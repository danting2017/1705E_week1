package com.liudan.cms.service.impl;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.lf5.util.StreamUtils;
import org.junit.Test;

import com.github.pagehelper.PageInfo;
import com.liudan.cms.domain.Article;
import com.liudan.cms.domain.Comment;
import com.liudan.cms.domain.User;
import com.liudan.cms.service.ArticleService;
import com.liudan.cms.service.CommentService;
import com.liudan.common.utils.DateUtil;
import com.liudan.common.utils.RandomUtil;
import com.liudan.common.utils.StreamUtil;
import com.liudan.common.utils.StringUtil;

public class CommentServiceImplTest extends JunitParent {

	@Resource
	private CommentService service;
	
	@Resource
	private ArticleService service1;
	
	@Test
	public void testInsert() {
		
		
		//1、设置登录的人，意思是谁评论
		User u = new User();
		
		
		//2、评论时间
		
		
		//3、评论文章
		//3.1先把文章查询出来
		Article a = new Article();
		a.setStatus(1);//审核通过的文章
		a.setDeleted(0);//没有被删除的文章
		
		PageInfo<Article> info = service1.selects(a, 1, 11);
		System.err.println(1);
		List<Article> list = info.getList();
		//3.2从List<Article>随机拿出一篇文章
		
		
		//4、评论内容
		//4.1随机生成字符串，最少100字以上
		
		
		for (int i = 0; i < 1000; i++) {
			Comment c = new Comment();
			//设置登录的人
			u.setId(130);
			c.setUser(u);
			//评论时间
			Calendar c1 = Calendar.getInstance();
			c1.set(2019, 0, 1, 0, 0, 0);
			Date date = DateUtil.randomDate(c1.getTime(), new Date());
			c.setCreated(date);
			//评论文章
			Article article2 = list.get(RandomUtil.random(0, list.size()-1));
			c.setArticle(article2);
			//随机评论内容（150字以上）
			String content = StringUtil.randomChineseString2(100);
			c.setContent(content);
			service.insert(c);
		}
		
	}

	@Test
	public void testSelects() {
		
	}

}
