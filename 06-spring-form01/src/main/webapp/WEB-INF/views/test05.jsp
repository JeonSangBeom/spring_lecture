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
	form label span {display:inline-block; m argin:0 20px; width:80px;}
	form div {margin-bottom:10px;}
</style>
<body>
	<h1>Form</h1>
	<div>
		<form:form modelAttribute="memberDto" method="POST" action="Result.do">
			<div><label><span>no : </span><form:input path="no" /></label></div>
			<div><label><span>name : </span><form:input path="name" /></label></div>
			<div><label><span>id : </span><form:input path="id" /></label></div>
			<div><label><span>password : </span><form:password path="password" showPassword="true" /></label></div>
			<form:hidden path="name"  /><!-- 안보이게 name으로 설정된 id와 name 값이 넘어간다-->
			<form:textarea path="name"  /> <!-- name 값 넣기  -->
			<form:button disabled="true">확인</form:button><!-- 클릭 못하게 설정  -->
		</form:form>
		<!-- path로 설정된 아이디 와 name 값이 넘어간다-->
	</div>
</body>
</html>