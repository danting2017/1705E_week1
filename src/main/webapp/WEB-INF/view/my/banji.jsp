<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function a() {
		var u = $("#b")
		alert(u);
	}
	
</script>
</head>
<body>
编辑次文章的栏目的id
<input type="text" onchange="a()" id="b"><br/>
编辑次文章分类的id
<input type="text" onchange="a()" id="b">
</body>
</html>