<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello Spring</h1>
	<hr />
	
	<form method="POST" action="Test01.do">
		<div><label><span>data01</span><input type="text" name="data01"></label></div>
		<div><label><span>data02</span><input type="text" name="data02"></label></div>
		<button>Test01.do로 전송</button>
	</form>
	<hr />
	<form method="POST" action="MemberTest.do">
		<div><label><span>no</span><input type="text" name="no"></label></div>
		<div><label><span>id</span><input type="text" name="id"></label></div>
		<div><label><span>name</span><input type="text" name="name"></label></div>
		<div><label><span>password</span><input type="text" name="password"></label></div>
		<button>MemberTest.do로 전송</button>
	</form>
	<hr />
	<form method="POST" action="MemberTest02.do">
		<div><label><span>no</span><input type="text" name="no"></label></div>
		<div><label><span>id</span><input type="text" name="id"></label></div>
		<div><label><span>name</span><input type="text" name="name"></label></div>
		<div><label><span>password</span><input type="text" name="password"></label></div>
		<button>MemberTest02.do로 전송</button>
	</form>
	
	<hr />
	<form method="POST" action="BoardTest.do">
		<legend>Board</legend>
		<div><label><span>no</span><input type="text" name="no"></label></div>
		<div><label><span>subject</span><input type="text" name="subject"></label></div>
		<div><label><span>name</span><input type="text" name="name"></label></div>
		<div><label><span>password</span><input type="text" name="password"></label></div>
		<div><label><span>regDate</span><input type="text" name="regDate"></label></div>
		<button>BoardTest.do 전송</button>
	</form>
	
</body>
</html>