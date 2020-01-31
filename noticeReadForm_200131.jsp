<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[게시판 읽기 폼]</title>
<script type="text/javascript">
	function download(notice_no){
		location.href="download?notice_no="+notice_no;
	}

	function noticeUpdateForm(notice_no){
		location.href="noticeUpdateForm?notice_no="+notice_no;
	}
</script>
</head>
<body>
	<h1>[게시글 읽기 폼]</h1>
	글번호 : ${notice.notice_no}<br/>
	글제목 : ${notice.notice_title}<br/>
	작성자 : ${notice.notice_name}<br/>
	조회수 : ${notice.notice_hits}<br/>
	글내용 : ${notice.notice_context}<br/>
	작성일 : ${notice.notice_indate}<br/>
	첨부파일 	
		<c:if test="${notice.notice_originfile != null}">
			<a href="javascript:download('${notice.notice_no}')">${notice.notice_originfile}</a>
		</c:if>
	<br/>
	<a href="javascript:noticeUpdateForm('${notice.notice_no}')">수정하기</a><br>
	
	<a href="/notice/noticeList">게시판으로 이동</a><br/>
	<a href="/">메인으로 이동</a><br/>
</body>
</html>