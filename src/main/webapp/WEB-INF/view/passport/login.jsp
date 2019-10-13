<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>欢迎回来</title>

<!-- Bootstrap -->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<link rel="icon" type="image/x-icon" href="/resource/pic/title.png" />

</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>

	<!-- 登录注册页面 -->
	<div style="height: 20px;"></div>

	<div class="container">
		<div class="row passport_bg">
			<div class="col-md-6">
				<div class="card" style="width: 30rem;">
					<div class="card-body">
						${error }
						<h3 class="card-title" align="center" style="color: yellow;">用户登录</h3>
						<form action="/passport/login" method="post" id="form1">
							<div class="form-group">
								<label for="user"><h5>用户名：</h5> </label> <input type="text"
									name="username" id="user" class="form-control" value="${username }">
							</div>
							<div class="form-group">
								<label for="password"><h5>密码：</h5> </label> <input type="password"
									name="password" id="password" class="form-control">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-info">登录</button>
								&nbsp;&nbsp;
								<button type="reset" class="btn btn-warning">重置</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="passport_r">
					<h3>最新加入的用户：</h3>
					<p align="center">
						<img src="/resource/images/15.jpg" width="150px;" height="180px;" alt="..."
							class="rounded-circle img-thumbnail" /> &nbsp;&nbsp;&nbsp;&nbsp;
						<img src="/resource/images/19.jpg" width="150px;" height="180px;" alt="..."
							class="rounded-circle img-thumbnail" />
					</p>
				</div>
			</div>
		</div>
	</div>
	<div>
		<br /> <br />
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
	
	<!-- 使用validate验证插件来判断前端验证 -->
	<script type="text/javascript">
		$("#form1").validate({
			rules:{
				username:{
					required:true,
				},
				password:{
					required:true,
				}
			},
			messages:{
				username:{
					required:"用户名不可以为空",
				},
				password:{
					required:"密码不可以为空",
				}
			}
			
		})
	</script>
</body>
</html>