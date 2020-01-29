<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[게시판]</title>
</head>
<body>
	<h1>게시글 등록 폼</h1>
	
	<form action="/notice/noticeInsert" method="post"  enctype="multipart/form-data">
		제목 : <input type="text" name="notice_title"><br/>
		내용 : <textarea name="notice_context"></textarea> <br/>
		작성자 : <input type="text" name="notice_name"><br/>
		첨부파일 : <input type="file" name="upload"><br/>
		<input type="submit" value="등록">
	</form>
	
	
</body>
</html>