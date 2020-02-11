<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[글 수정]</title>
<script type="text/javascript">

	function formCheck(){
		var board_title = document.getElementById("board_title");
		var board_content = document.getElementById("board_content");
		if(board_title.value == "" || board_title.value.length == 0){
			alert("제목을 입력해 주세요.");
			return false;
		}
		if(board_content.value == "" || board_content.value.length == 0){
			alert("내용을 입력해 주세요.");
			return false;
		}
		return true;
	}

	function fileDownload(board_no){
		location.href="download?board_no=" + board_no;
	}
</script>
</head>
<body>
	<form action="boardUpdate" method="post" onsubmit="return formCheck();" enctype="multipart/form-data"> 
		글 제목 : <input type="text" id="board_title" name="board_title" value="${board.BOARD_TITLE}"><br/>
		글 내용 : <textarea id="board_content" name="board_content">${board.BOARD_CONTENT}</textarea><br/>
		조회수 : ${board.BOARD_HITS}<br/>
		등록일 : ${board.BOARD_INDATE}<br/>
		첨부파일 : <input type="file" name="upload"><br/>
		<c:if test="${board.BOARD_SAVEDFILE != null}">
			<a href="javascript:fileDownload('${board.BOARD_NO}')">${board.BOARD_ORIGINFILE}</a>
		</c:if>
		<input type="hidden" name="board_no" value="${board.BOARD_NO}"><br/>
		<input type="submit" value="글 수정">
	</form>	
	<a href="/board/boardList">게시판 이동</a> <br/>
	<a href="/">홈으로 이동</a>
</body>
</html>