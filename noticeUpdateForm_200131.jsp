<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[게시판]</title>
</head>
<body>
	<h1>게시글 수정 폼</h1>
	
	<form action="/notice/noticeUpdate" method="post"  enctype="multipart/form-data">
		제목 : <input type="text" name="notice_title" value="${notice.notice_title}"><br/>
		내용 : <textarea name="notice_context">${notice.notice_context}</textarea> <br/>
		작성자 : <input type="text" name="notice_name" value="${notice.notice_name}" ><br/>
		첨부파일 : <input type="file" name="upload" ><br/>
			<c:if test="${notice.notice_originfile != null}">
				${notice.notice_originfile}
			</c:if>
		<input type="hidden" name="notice_no" value="${notice.notice_no}">
		<input type="submit" value="수정">	
	</form>
	<br>
	<a href="/notice/noticeList">게시판으로 이동</a><br/>
	<a href="/">메인으로 이동</a><br/>
	
</body>
</html>