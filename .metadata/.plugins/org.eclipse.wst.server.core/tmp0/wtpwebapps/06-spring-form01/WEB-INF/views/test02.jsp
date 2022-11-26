<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<!-- form을 input으로 사용하는 방법(스프링에서 제공하고 있는 taglib를 이용하는 것) -->
	<h1>Form</h1>  
	<div>
		<form:form modelAttribute="memberDto" method="POST" action="Result.do">
			<div><label><span>no : </span><form:input path="no" /></label></div><!-- 자동으로 id name type value가 붙어 나온다  -->
			<div><label><span>name : </span><form:input path="name" /></label></div>
			<div><label><span>id : </span><form:input path="id" /></label></div>
			<div><label><span>password : </span><form:password path="password" showPassword="true" /></label></div>
			<!-- showPassword="true" - password가 보이게 해주기 -->
			<button>전송</button>
		</form:form>
	</div>
</body>
</html>