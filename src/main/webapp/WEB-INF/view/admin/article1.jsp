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
	<script type="text/javascript"
		src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">


</script>
</head>
<body>

	<div class="container" align="center">
		<dl>
			<dt><h1>${article.title }</h1></dt>
			<dd>${article.user.nickname} &nbsp; <fmt:formatDate value="${article.updated }" pattern="yyyy-MM-dd HH:mm:ss"/> </dd>
				<hr style="height:1px;border:none;border-top:1px dashed #0066CC;"/>
			<dd style="float: right;"><button type="button" class="btn btn-success" onclick="update(1,${article.id})" ${article.status==1?"disabled":""}>同意</button>
			<button type="button" class="btn btn-warning" onclick="update(-1,${article.id})" ${article.status==-1?"disabled":""}>驳回</button></dd>
		
			<dd>${article.content }</dd>
		</dl>
	</div>


</body>
<script type="text/javascript">
//审核文章
function update(status,id){
	$.post("/article/update",{id:id,status:status},function(flag){
	if(flag){
		alert("操作成功");
		window.close();//关闭当前页面
	}	
	})
}
</script>
</html>