<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	div {margin-bottom:50px}
	form label span {display:inline-block; margin:0 20px; width:80px;}
	form div {margin-bottom:10px;}
</style>
<body>
	<h1>Form</h1>
	<div>
		<form method="POST" action="Result.do">
			<div><label><span>no : </span><input type="text" name="name" value="${memberDto.no}"></label></div>
			<div><label><span>name : </span><input type="text" name="name" value="${memberDto.name}"></label></div>
			<div><label><span>id : </span><input type="text" name="name" value="${memberDto.id}"></label></div>
			<div><label><span>password : </span><input type="text" name="name" value="${memberDto.password}"></label></div>
			<button>전송</button>
		</form>
	</div>
</body>
</html>