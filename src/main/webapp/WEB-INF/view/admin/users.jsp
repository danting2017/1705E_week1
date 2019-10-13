<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function query() {
		var url = "/user/selects?username=" + $("[name='username']").val();
		//在框架的中级区域加载
		$("#content-wrapper").load(url);
	}
	
	//改变用户状态
	function update(id,obj){
		//alert($(obj).text())
	   var locked =$.trim($(obj).text())=="正常"?1:0;
		$.post("/user/update",{id:id,locked:locked},function(msg){
			if(msg){ 	
			
				$(obj).text(locked==1?"禁用":"正常")
				  //改变按钮的样式 颜色
				  $(obj).attr("class",locked=="1"?"btn btn-warning":"btn btn-success");
			}
			
		
		}
	}
	
	
</script>
</head>
<body>
	<div class="container">
		<div class="form-group form-inline">
			用户名：<input type="text" name="username" class="form-control"
				value="${username }">&nbsp;&nbsp;
			<button type="button" class="btn btn-danger" onclick="query()">查询</button>

		</div>
		<table class="table table-bordered" style="text-align: center;">
			<tr>
				<td>序号</td>
				<td>用户名</td>
				<td>昵称</td>
				<td>性别</td>
				<td>生日</td>
				<td>注册日期</td>
				<td>修改日期</td>
				<td>状态</td>
			</tr>
			<c:forEach items="${list}" var="l" varStatus="i">
				<tr>
					<td>${i.index+1 }</td>
					<td>${l.username}</td>
					<td>${l.nickname}</td>
					<td>${l.gender==0?'男':'女'}</td>
					<td><f:formatDate value="${l.birthday}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><f:formatDate value="${l.created}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><f:formatDate value="${l.updated}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><c:if test="${l.locked==0}">
							<button type="button" class="btn btn-success"
								onclick="update(${l.id},this)">正常</button>
								
						</c:if> <c:if test="${l.locked==1}">

							<button type="button" class="btn btn-success"
								onclick="update(${l.id},this)">禁用</button>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>${pages}</div>

	<script type="text/javascript">
		$(".page-link").click(function() {
			var url = $(this).attr("data");
			$("#content-wrapper").load(url);
		})
		
		
		function update(id,obj){
		//alert($(obj).text())
	   var locked =$.trim($(obj).text())=="正常"?1:0;
		$.post("/user/update",{id:id,locked:locked},function(msg){
			if(msg){
			
				$(obj).text(locked==1?"禁用":"正常")
				  //改变按钮的样式 颜色
				  $(obj).attr("class",locked=="1"?"btn btn-warning":"btn btn-success");
			}
			
		})
		
	}
	</script>
</body>
</html>