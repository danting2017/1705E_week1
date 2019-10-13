<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章详情</title>
<!-- 引入bootstrap样式 -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
</head>
<body>
	<div class="row">
		<!-- 代表的是一行 -->
		<div class="container col-md-9" align="center">

			<div>
				<button type="button" class="btn btn-info" onclick="myup()"
					id="myup">上一篇</button>

				<button type="button" class="btn btn-info" onclick="mydown()"
					id="mydown">下一篇</button>
			</div>

			<dl>
				<dt>
					<h1>${article.title }</h1>
				</dt>
				<dd>${article.user.nickname}
					&nbsp;
					<fmt:formatDate value="${article.updated }"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</dd>
				<hr
					style="height: 1px; border: none; border-top: 1px dashed #0066CC;" />
				<dd>${article.content }</dd>
			</dl>

			<!-- 文章的评论 -->
			<div>

				<h3 style="color: red;">文章评论</h3>
				<c:if test="${sessionScope.user!=null}">
					<div>
						<form id="form1">
							<textarea rows="10" cols="155" name="content">
				</textarea>
							<input type="hidden" name="article.id" value="${article.id }">
							<input type="button" class="btn btn-info" value="评论"
								onclick="addComment()">
						</form>
					</div>
				</c:if>
				<c:if test="${sessionScope.user==null }">
					<span style="color: blue;">请登录后再发布评论</span>
				</c:if>


				<dl>
					<c:forEach items="${comment}" var="c">
						<dt style="text-align: left;">${c.user.nickname}&nbsp;&nbsp;
							<fmt:formatDate value="${c.created}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</dt>
						<dd style="text-align: left;">${c.content}</dd>
						<hr />
					</c:forEach>
					${pages}
				</dl>
			</div>
		</div>

		<div class="col-md-3">
			<div class="card" style="width: 18rem;">
				<div class="card-body">
					<h4 class="card-title">评论排行榜</h4>
					<c:forEach items="${comment1}" var="l" varStatus="n">
						${n.index+1 }.
						${l.content}	
						<br>		
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	function myup() {//前一篇
		//执行方法前要检查 文章存不存在
		$.get("/article/checkPre", {
			id : '${article.id}',
			channelId : '${article.channelId}'
		}, function(flag) {
			if (flag) {//如果前一篇有内容则执行查询
				location.href = "/article/selectByPre?id=" + '${article.id}'
						+ "&channelId=" + '${article.channelId}'
			} else {
				alert("别点了,到头了")
			}
		})

	}
	function mydown() {
		//执行方法前要检查 文章存不存在
		$.get("/article/checkSuf", {
			id : '${article.id}',
			channelId : '${article.channelId}'
		}, function(flag) {
			if (flag) {//如果下一篇有内容则执行查询
				location.href = "/article/selectBySuf?id=" + '${article.id}'
						+ "&channelId=" + '${article.channelId}'
			} else {
				alert("别点了,到头了")
			}
		})

	}
	//提交评论
	function addComment() {
		
		$.post("/article/comment", $("#form1").serialize(), function(flag) {
			if (flag) {
				alert("评论成功");
				location.href="/article/list";
				location.reload();//刷新当前页面
			} else {
				alert("评论失败，检查是否登录");
			}
		})
	}

	$(".page-link").click(function() {
		var url = $(this).attr("data");
		location.href = url + "&id=" + '${article.id}';
	});
</script>
</html>