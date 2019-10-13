<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>CMS系统</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>

	<div>
		<br />
	</div>
	<div class="container">
		<div class="row">
			<!-- 栏目-->
			<div class="col-md-2 ">
				<ul class="list-group">
					<li id="channel" class="list-group-item text-center"><a
						href="/" class="channel">热门</a></li>
					<c:forEach items="${channels}" var="c">
						<li class="list-group-item text-center" id="channel${c.id }">
							<a href="/?channelId=${c.id }" class="channel">${c.name }</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<!-- 中间内容主体区 -->
			<div class="col-md-7 split min_h_500">
				<!-- 判断是否显示文章 如果栏目==null  怎默认显示轮播和默认文章 -->
				<c:if test="${article.channelId==null }">
					<!--默认显示  -->
					<div id="default">
						<!-- 轮播图 -->
						<div id="carousel">
							<div class="bd-example">
								<div id="carouselExampleCaptions" class="carousel slide"
									data-ride="carousel">
									<ol class="carousel-indicators">
										<li data-target="#carouselExampleCaptions" data-slide-to="0"
											class="active"></li>
										<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
										<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
									</ol>
									<div class="carousel-inner">
										<c:forEach items="${slides}" var="s" varStatus="i">
											<div class="carousel-item ${i.index==0?"active":""}">
												<img src="/pic/${s.url}" class="d-block w-100"
													width="120px;" height="220px;" alt="...">
												<div class="carousel-caption d-none d-md-block">
													<h5>${s.title }</h5>
												</div>
											</div>
										</c:forEach>
									</div>
									<a class="carousel-control-prev"
										href="#carouselExampleCaptions" role="button"
										data-slide="prev"> <span
										class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									</a> <a class="carousel-control-next"
										href="#carouselExampleCaptions" role="button"
										data-slide="next"> <span
										class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									</a>
								</div>
							</div>
						</div>
						<br />
						<!-- 热门文章 -->
						<div id="hot">
							<ul class="list-unstyled">
								<c:forEach items="${list}" var="l">
									<li class="media"><img src="/pic/${l.picture }"
										class="mr-3" width="190px;" height="124px;" alt="...">
										<div class="media-body">
											<h4 class="mt-0 mb-1 channel">
												<a href="/article/selectByUser?id=${l.id}" target="_blank">${l.title}</a>
											</h4>
											<br> <br> ${l.user.nickname}
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<fmt:formatDate value="${l.updated}"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</div></li>
									<hr />
								</c:forEach>
							</ul>
						</div>
						<div>${pages }</div>

					</div>
				</c:if>
				<!-- 如果栏目不等于null，则不显示轮播图和默认文章 -->
				<c:if test="${article.channelId != null}">
					<div id="category">
						<!-- 分类 -->
						<ul class="nav">
							<li class="nav-item" id="cat"><a class="nav-link active"
								href="/?channelId=${article.channelId}">全部</a></li>
							<c:forEach items="${category}" var="l">
								<li class="nav-item" id="cat${l.id }"><a
									class="nav-link active"
									href="/?channelId=${article.channelId}&categoryId=${l.id}">${l.name }</a>
								</li>
							</c:forEach>


						</ul>
					</div>

					<ul class="list-unstyled">
						<c:forEach items="${articles}" var="l">
							<li class="media"><img src="/pic/${l.picture }" class="mr-3"
								width="190px;" height="124px;" alt="...">
								<div class="media-body">
									<h4 class="mt-0 mb-1 channel">
										<a href="/article/selectByUser?id=${l.id}" target="_blank">${l.title}</a>
									</h4>
									<br> <br> ${l.user.nickname}
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<fmt:formatDate value="${l.updated}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</div></li>
							<hr />
						</c:forEach>
					</ul>


					<div>${pages }</div>
			</div>
			
			</c:if>



		</div>

		<!-- 最新文章 -->
		<div class="col-md-3 split min_h_500">
			<div class="card" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title channel" style="color: red">最新文章</h5>
					<ul>
						<c:forEach items="${list1}" var="l">
							<li><a href="/article/selectByUser?id=${l.id}" target="_blank">${l.title}</a> </li>
						</c:forEach>

					</ul>
				</div>
			</div>
		</div>
	</div>
	</div>

	<br />

	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
	<script type="text/javascript">
		/* 给导航条添加选中样式 */
		$(function() {
			$("#channel${article.channelId}").addClass(
					"list-group-item-warning");
			$("#cat${article.categoryId}").addClass("list-group-item-warning");
		})

		$(".page-link").click(function() {
			var url = $(this).attr("data");
			var channelId ='${article.channelId}';
			var categoryId ='${article.categoryId}';
			location.href=url+"&channelId="+channelId+"&categoryId="+categoryId
		});
	</script>
</body>
</html>