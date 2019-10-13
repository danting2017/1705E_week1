<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>KindEditor JSP</title>
<link rel="stylesheet"
	href="/resource/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="/resource/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8"
	src="/resource/kindeditor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="/resource/kindeditor/kindeditor-all.js"></script>
    
<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
<script src="/resource/js/jquery-3.2.1.js"></script>

<script>
	KindEditor.ready(function(K) {
		window.editor1 = K.create('textarea[name="content1"]', {
			cssPath : '/resource/kindeditor/plugins/code/prettify.css',
			uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
	function query() {
		alert(editor1.html())
		//alert( $("[name='content1']").attr("src"))
	}
</script>
</head>
<body>
	<%=htmlData%>
	<form id="form1">
		<div class="form-group">
			<label for="title"><h3 style="color: red; font-size: 24px;">文章标题</h3></label>
			<input type="text" name="title" id="title" class="form-control">
		</div>
		<textarea name="content1" cols="100" rows="8"
			style="width: 825px; height: 260px; visibility: hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<div class="form-group form-inline">
			栏目： <select name="channelId" id="channel" class="form-control">
				<option value="">请选择</option>
			</select> 分类： <select name="categoryId" id="category" class="form-control">
			</select>
		</div>

		<div class="form-group">
			标题图片： <input type="file" name="file" class="form-control">
		</div>

		<input type="button" name="button" value="发布文章" class="btn btn-info"
			onclick="publish()" />
	</form>
</body>
<!-- 使用文档就绪函数来给栏目和分类制造成二级联动 -->
<script type="text/javascript">
	/* 发布文章 */
	function publish() {
		//序列化表单数据 带文件
		 var formData = new FormData($( "#form1" )[0]);
		//封装富文本中的html内容
		formData.set("content",editor1.html());
		
		$.ajax({
			type:"post",
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
		   data:formData,
		   url:"/article/publish",
		   success:function(flag){
			   if(flag){
				   alert("发布成功");
				   location.href="/my/";
			   }else{
				   alert("发布失败");
			   }
		   }
			
			
			
			
			
		})
		
		
	}

	//写栏目的方法
	$(function() {
		$.get("/channel/selects", function(list) {
			for ( var i in list) {
				$("#channel").append(
						"<option value='"+list[i].id+"'>" + list[i].name
								+ "</option>");
			}
		});
	})

	//写分类的方法
	//首先获取栏目下的id值
	$("[name=channelId]").change(
			function() {
				//先清空分类下的代码，然后在追加
				$("#category").empty();
				var channelId = $(this).val();
				alert(channelId);

				$.get("/category/selects", {
					channelId : channelId
				}, function(list) {
					for ( var i in list) {
						$("#category").append(
								"<option value='"+list[i].id+"'>"
										+ list[i].name + "</option>");
					}
				});
			})
</script>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>