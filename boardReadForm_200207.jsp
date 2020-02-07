<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[글 읽기]</title>
</head>
<body>
	글 제목 : ${board.BOARD_TITLE} <br/>
	글 내용 : ${board.BOARD_CONTENT} <br/>
	글쓴이 : ${board.MEMBER_NM} <br/>
	조회수 : ${board.BOARD_HITS} <br/>
	작성일 : ${board.BOARD_INDATE} <br/>
	첨부파일
		<c:if test="${board.BOARD_SAVEDFILE != null }">
			${board.BOARD_ORIGINFILE}
		</c:if>
	<br/>
	<a href="/board/boardWriteForm">글쓰기</a> <br/>
	<a href="/board/boardList">게시판 이동</a> <br>
	<a href="/">홈으로 이동</a>
</body>
</html>