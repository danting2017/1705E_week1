package com.liudan.cms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.liudan.cms.domain.Article;
import com.liudan.cms.service.ArticleService;
import com.liudan.common.utils.DateUtil;
import com.liudan.common.utils.RandomUtil;
import com.liudan.common.utils.StreamUtil;

public class ArticleServiceImplTest extends JunitParent{

	//注入文章的service
	@Resource
	private ArticleService service;
	
	@Test
	public void testSelects() {
		
	}

//	批量导入文章
	@Test
	public void as () throws FileNotFoundException {
		
		//根据路径初始化一个对象
		File file = new File("D:\\articles");
		//获取目录下的所有文章
		File[] files = file.listFiles();
		System.out.println(files);
		//遍历此文章数组
		for (File file2 : files) {
			//创建一个文章对象
			Article article = new Article();
			//可以获取文章标题
			String name = file2.getName();
			//去除后缀名
			String title = name.substring(0, name.lastIndexOf("."));
			article.setTitle(title.trim());//封装标题
			//封装获取文章的内容
			
			//调用工具类
			String content = StreamUtil.readTextFile(file2);
			article.setContent(content);
			
			//截取前150个字作为文章摘要
			article.setSummary(content.substring(0, 150));
			
			//设置点击量
			article.setHits(RandomUtil.random(0, Integer.MAX_VALUE-1));
			//设置是否热门
			article.setHot(RandomUtil.random(0, 1));
			//文章发布日期,默认为5月1号至今
			Calendar c = Calendar.getInstance();
			c.set(2019, 6, 1, 0, 0,0);
			Date date = DateUtil.randomDate(c.getTime(), new Date());
			article.setCreated(date);//封装发布日期
			//封装其他的字段
			article.setDeleted(0);
			article.setUserId(130);
			article.setPicture("6.jpg");
			article.setUpdated(new Date());
			article.setStatus(1);//默认审核过
			article.setComments(0);//设置评论的初始化的数据为0条
			service.insertSelective(article);//执行插入
		}
		
		
		/*
		//根据路径初始化一个File对象
		File file = new File("D:/article");
		//获取目录下所有文件
		File[] files = file.listFiles();
//		/遍历文件
		for (File file2 : files) {
		
			Article article = new Article();
			
				//把制造业搞上去.txt
				String name = file2.getName();//获取文件名称,带文件后缀
				//去掉文件后缀
				String title = name.substring(0, name.lastIndexOf("."));
				
			article.setTitle(title);//1封装TITLE
			//封装文章内容 .调用工具类
			String content = StreamUtil.readTextFile(file2);
			article.setContent(content);//2封装文章内容
			//3在文本内容中截取前140个字作为摘要
			
			article.setSummary(content.substring(0, 140));
			//4“点击量”和“是否热门”、“频道”字段要使用随机值
			
			article.setHits(RandomUtil.random(0, Integer.MAX_VALUE-1));//点击量
			
			article.setHot(RandomUtil.random(0, 1));//是否热门
			
			int channelId = RandomUtil.random(1, 9);
			article.setChannelId(channelId);//栏目ID
			//根据栏目ID 查询分类
			List<Category> list = service1.selects(channelId);
			//System.out.println("=======================>"+(list.size()-1)+"channelId="+channelId);
			Category category = list.get(RandomUtil.random(0, list.size()-1));
			
			article.setCategoryId(category.getId());//分类ID
			//文章发布日期从2019年1月1日模拟到今天
			Calendar c = Calendar.getInstance();
			c.set(2019, 0, 1, 0, 0, 0);
			
			Date date = DateUtil.randomDate(c.getTime(), new Date());
			
			article.setCreated(date);//文章发布日期
			article.setUpdated(date);//修改日期
			article.setUserId(130);
			article.setStatus(1);//默认审核过
			article.setDeleted(0);//默认未删除
			article.setPicture("048e6377-f49a-4ac0-a81f-c692fd3ac0e3.jpg");
			service.insertSelective(article)	;//执行插入
			*/
		}
		
		
	

	@Test
	public void testSelectByPrimaryKey() {
		
	}

	@Test
	public void testUpdateByPrimaryKeyWithBLOBs() {
		
	}

}
