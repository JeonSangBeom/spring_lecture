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
	<h1>Form</h1>
	<div>
		<form:form modelAttribute="selectDto" method="POST" action="Result.do">
			<div>
				<form:select path="data01">
					<form:option value="data01">옵션01</form:option>
					<form:option value="data02">옵션02</form:option>
					<form:option value="data03">옵션03</form:option>
				</form:select>
				<!-- path와 value가 맞으면 자동으로 selected가 생성된다 -->
			</div>
			<div>
				<form:select path="data02">
					<form:options items="${selectDto.dataList }" />
					<!--"{selectList}"도 가능 - selectDto에 실어둔 값이나 model에 실어둔 값이 둘다 있기 때문에 두가지로 불러올 수 있다 -->
				</form:select>
			</div>
			 <!-- path에 있는 data02는 modelAttribute="selectDto"에 지정한 data02이다 
			 	form:options - 배열을 넣을 수 있다 -->
			<div>
				<form:select path="data03">
					<form:options items="${animalList }" />
				</form:select>
			</div>
			<div>
				<form:select path="data04">
					<form:options items="${animalList02 }" itemLabel="key" itemValue="value"  />
				</form:select>
			</div>
			<div>
				<form:checkbox path="data05" value="tigers" />기아 타이거즈
				<form:checkbox path="data05" value="lions" />삼성 라이언즈
				<form:checkbox path="data05" value="giants" />롯데 자이언츠
				<form:checkbox path="data05" value="bears" />두산 베어스
			</div>
			<div>
				<form:checkboxes path="data03" items="${animalList }" />
			</div>
			<div>
				<form:checkboxes path="data04" items="${animalList02 }" itemLabel="key" itemValue="value"  /> 
				<!-- key value로 값을 실었을땐 itemLabel="key" itemValue="value" 이것을 꼭 써줘야 한다  -->
			</div>
		</form:form>
	</div>
	
	
	
	
</body>
</html>