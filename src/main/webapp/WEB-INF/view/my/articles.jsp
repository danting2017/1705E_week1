<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章列表</title>
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>


</head>
<body>
	<div style="padding: 0 10px 0 10px" class="form">

		<!-- 所搜文章 -->
		<div class="form-group form-inline">
			标题:<input type="text" name="title" class="form-control"
				value="${article.title }"> &nbsp;
			<button type="button" class="btn btn-info" onclick="query()">查询</button>
		</div>

		<ul class="list-unstyled">
			<c:forEach items="${list}" var="a">
				<li class="media form-group"><img src="/pic/${a.picture }"
					class="mr-3" alt="..." style="width: 190px; height: 124px">
					<div class="media-body">
						<h4 class="mt-0 mb-1">
							<a href="javascript:myOpen(${a.id })">${a.title }</a>
						</h4>
						<br> <br>
						<h5>${a.user.nickname }
							&nbsp;
							<fmt:formatDate value="${a.updated }"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</h5>
						<span style="float: right">
							<button class="btn btn-success" onclick="updateByid(${a.id})">
								编辑</button>
							<button class="btn btn-warning" onclick="update(${a.id})">
								修改</button>
						</span>
					</div></li>
				<hr />
			</c:forEach>
		</ul>

		${pages }
	</div>
</body>

<script type="text/javascript">


//修改文章
function update(id){
	var url="/article/update?id="+id
	$("#center").load(url)
}
//修改文章的分类
function updateByid(id){
	
	var url="/article/updateByid?id="+id
	$("#center").load(url)
}


	// 文章标题搜索的方法
	function query() {
		//在当前页面加载 url
		var url = "/article/selectsByuser?title=" + $("[name='title']").val();
		$("#center").load(url);
	}

	function myOpen(id) {
		//在新窗口打开文章详情
		window.open("/article/selectByUser?id=" + id, "_blank")
	}
	//分页的下一页，上一页
	$(".page-link").click(function() {
		var url = $(this).attr("data");
		//location.href=url;
		$("#center").load(url);
	});
</script>
</html>