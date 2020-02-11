<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[글 읽기]</title>
<script type="text/javascript">
	function fileDownload(board_no){
		location.href="download?board_no=" + board_no;
	}
	function boardDelete(board_no){
		if(confirm("정말 삭제하시겠습니까?")){
			location.href="boardDelete?board_no=" + board_no;	
		}	
	}
	function boardUpdateForm(board_no){
		location.href="boardUpdateForm?board_no=" + board_no;
	}
</script>
</head>
<body>
	글 제목 : ${board.BOARD_TITLE} <br/>
	글 내용 : ${board.BOARD_CONTENT} <br/>
	글쓴이 : ${board.MEMBER_NM} <br/>
	조회수 : ${board.BOARD_HITS} <br/>
	작성일 : ${board.BOARD_INDATE} <br/>
	첨부파일 
		<c:if test="${board.BOARD_SAVEDFILE != null }">
			<a href="javascript:fileDownload('${board.BOARD_NO}')">${board.BOARD_ORIGINFILE}</a>
		</c:if>
	<br/>
	
	<c:if test="${board.MEMBER_ID == sessionScope.loginId}">
		<input type="button" value="수정" onclick="boardUpdateForm('${board.BOARD_NO}')">
		<input type="button" value="삭제" onclick="boardDelete('${board.BOARD_NO}')">
	</c:if>
	<br/>
	<a href="/board/boardWriteForm">글쓰기</a> <br/>
	<a href="/board/boardList">게시판 이동</a> <br>
	<a href="/">홈으로 이동</a>
</body>
</html>