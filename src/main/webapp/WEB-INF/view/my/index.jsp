<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>我的个人空间</title>

</head>
<body>
	<!-- 头部部分 -->
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<br />
	<!-- 横幅 -->
	<div class="container">
		<div class="row">
			<div class="col-md-12 my_banner"></div>
		</div>
	</div>
	<br />
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<!-- 代表的是一行 -->
			<div class="col-md-3">
				<!-- 一行里面有12列     col-md-3代表占几列 -->
				<!-- 导航条 -->
				<jsp:include page="/WEB-INF/view/my/left.jsp"></jsp:include>
				<br />
			</div>
			<div class="col-md-9 ">
				<!-- 中间内容区域 -->
				<div id="center">

					<div style="display: none">
						<!-- 引入富文本编辑器的样式 -->
						<jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
					</div>

				</div>

			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/view/common/footer.jsp" />

<script type="text/javascript" src="/resource/js/cms.js"></script>

	<script type="text/javascript">
		$(function(){
			$("#myArticle").click();
		})
	</script>
</body>
</html>