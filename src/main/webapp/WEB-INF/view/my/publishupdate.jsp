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
<!-- jquery.validate 校验提示样式 -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/jquery/screen.css">
	<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
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
		<input type="hidden" name="id" value="${article.id }">

			<label for="title"> 文章标题:</label> <input type="text"
				class="form-control" id="title" name="title" value="${article.title }">
		</div>
		<textarea name="content1" cols="100" rows="8"
			style="width: 825px; height: 260px; visibility: hidden;" ><%=htmlspecialchars(htmlData)%>
			${article.content}
			</textarea>
		<br />
		<div class="form-group form-inline" >
			栏目: <select name="channelId"   class="form-control" id="channel">
				<option value="-1">请选择</option>

			</select> 分类: <select name="categoryId" class="form-control" id="category"></select>
		</div>
		<div class="form-group ">

			标题图片: <input type="file" class="form-control" name="file">
		</div>
		<input type="s" name="button"  class="btn btn-info" value="修改文章" onclick="publish()" />
	</form>
</body>

<script type="text/javascript">
$(function(){
	$("#form1").validate({
		rules:{
			channelId:{
				min:1,
				required:true,
			},
			
		},
		messages:{
			
			channelId:{
				min:"栏目不能为空",
				required:"用户名不能为空",
			}
		},
		submitHandler: function(form) {
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
			   url:"/article/publishupdate",
			   success:function(flag){
				   if(flag){
					   alert("修改成功");
				   }else{
					   alert("修改失败");
				   }
			   }
				
				
				
				
				
			})
			
		}
		
		
	})
	
	
})








//发布文章
function publish(){
	$("#form1").submit();
	
	
}



$(function(){
	//为栏目加载对象
	$.get("/channel/selects",function(list){
		for(var i in list){
			$("#channel").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
		}
		$("#channel").val('${article.channelId}');//让下拉框选中
		if($("#channel").val()!=null){//如果分类有值则去查询栏目下的分类
			getCategory($("#channel").val());
			
			
		}
	})
	
	//为栏目提交change事件
	$("#channel").change(function(){
		//先清空分类下内容,在追加
		$("#category").empty();
		//获取当前栏目的ID
		var channelId =$(this).val();
		//去查询栏目下的所有分类
		getCategory(channelId);
	})
	
	
	
})


//获取分类
function getCategory(channelId){
	$.get("/category/selects",{channelId:channelId},function(list){
		for(var i in list){
			$("#category").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
		}
		//
		$("#category").val('${article.categoryId}');//让下拉框选中
	})
}

</script>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>