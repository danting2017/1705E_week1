<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章列表</title>
<script type="text/javascript">
	function query() {
		var url = "/article/selectsByAdmin?status="
				+ $("[name='status']").val();
		//在框架的中级区域加载url
		$("#content-wrapper").load(url);
	}

	$(function() {
		var status = '${article.status}';//获取当前状态
		$("[name='status']").val(status);//让下拉框选中

	})
</script>
</head>
<body>
	<div class="container">
		<div class="form-group form-inline">
			<select class="form-control" name="status">
				<option value="0">待审</option>
				<option value="1">已审</option>
				<option value="-1">驳回</option>
				<option value="">全部</option>
			</select> &nbsp;
			<button type="button" class="btn btn-success" onclick="query()">查询</button>
		</div>
		<table class="table table-bordered" style="text-align: center;">
			<tr>
				<td>序号</td>
				<td>文章标题</td>
				<td>作者</td>
				<td>发布时间</td>
				<td>状态</td>
				<td>点击量</td>
				<td>是否热门</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${list}" var="l" varStatus="i">
				<tr>
					<td>${i.index+1 }</td>
					<td>${l.title }</td>
					<td>${l.user.username }</td>
					<td><fmt:formatDate value="${l.updated }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${l.status==0?"待审":l.status==1?"已审":"驳回"}</td>
					<td>${l.hits }</td>
					<td>${l.hot==0?"否":"是" }</td>
					<td><a href="javascript:myOpen(${l.id })">文章详情</a></td>
				</tr>
			</c:forEach>
		</table>
		<div>${pages}</div>


	</div>

</body>
<script type="text/javascript">

	$(".page-link").click(function() {
		var url = $(this).attr("data");
		$("#content-wrapper").load(url);
	})

	function myOpen(id) {
		var url = "/article/selectByAdmin?id="+id;
		window.open(url, "ablank")
	}
</script>
</html>