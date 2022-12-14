<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INSERT</title>
<link rel="stylesheet" href="css/reset.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=PT+Serif:wght@400;700&family=Raleway:wght@100;200;300;400;500;600;700;800;900&family=Titillium+Web:wght@200;300;400;600;700;900&display=swap"
	rel="stylesheet" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link href="summernote/summernote.min.css" rel="stylesheet" />		
<link rel="stylesheet" href="css/layout.css" />
<link rel="stylesheet" href="css/form.css" />
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/main02.js" defer></script>

</head>
<body>
	<header id="header">
		<div class="inner"><h1>GALLERY</h1></div>
	</header>
	<form class="form" method="POST" action="InsertProcess.do" enctype="multipart/form-data"> 
	<!-- enctype 파일 업로드시 중요 / 멀티파트는 무조건 post로 날려야 한다 -->
		<table>
			<colgroup>
				<col style="width:20%;">
				<col> 
				<!-- 나머지는 알아서 채워진다 -->
			</colgroup>
			<tbody>
				<tr>
					<th>제목</th>
					<td class="titleWrap">
						<input type="text" name="title" placeholder="제목을 입력하세요." id="title">
						<div class="category">
							<label><input type="checkbox" name="category" value="travel"><span>여행</span></label>
							<label><input type="checkbox" name="category" value="movie"><span>영화</span></label>
							<label><input type="checkbox" name="category" value="study"><span>공부</span></label>
							<label><input type="checkbox" name="category" value="life"><span>일상</span></label>
						</div>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="contents" placeholder="내용을 입력해 주세요."></textarea>
					</td>
				</tr>
				<tr>
					<th>파일</th>
					<td>
						<div class="fileBox">
							<input type="text" class="fakeFile" placeholder="업로드할 파일을 선택해 주세요.">
							<label><input type="file" name="multipartFile"><span>파일찾기</span></label>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btns">
			<button type="submit" class="btn btnConfirm">확인</button>
			<button type="reset" class="btn btnCancel">취소</button>
		</div>
	</form>
</body>
</html>