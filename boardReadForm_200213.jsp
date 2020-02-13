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

	function replyDelete(reply_no, board_no){
		if(confirm("정말 삭제하시겠습니까?")){		
			location.href="replyDelete?reply_no=" + reply_no + "&board_no=" + board_no;
		}
	}
	function replyUpdateForm(reply_no, board_no, reply_content){
		var updateForm = document.getElementById("updateForm" + reply_no);
		var str = '';
		str += '<form action="replyUpdate" method="post">';
		str += '<input type="hidden" name="reply_no" value="'+reply_no+'">';
		str += '<input type="hidden" name="board_no" value="'+board_no+'">';
		str += '<input type="text" name="reply_content" value="'+reply_content+'">';
		str += '<input type="submit" value="수정">';
		str += '<input type="button" value="취소" onclick="replyCancel(\''+reply_no+'\')">';
		str += '</form>';

		updateForm.innerHTML = str;
	}
	function replyCancel(reply_no){
		var updateForm = document.getElementById("updateForm"+reply_no);
		updateForm.innerHTML = '';
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
	
	<form action="replyInsert" method="post">
		<input type="hidden" name="board_no" value="${board.BOARD_NO}">
		<input type="text" name="reply_content" placeholder="리플을 작성하세요.">
		<input type="submit" value="저장">
	</form>
	
	<table>
		<c:forEach items="${replyList}" var="reply">
			<tr>
				<td>${reply.reply_no}</td>
				<td>${reply.member_id}</td>
				<td>${reply.reply_content}</td>
				<td>${reply.reply_indate}</td>
				<td>
					<c:if test="${reply.member_id == sessionScope.loginId}">
						<input type="button" value="수정 " onclick="replyUpdateForm('${reply.reply_no}', '${reply.board_no}', '${reply.reply_content}')">
					</c:if>
				</td>
				<td>
					<c:if test="${reply.member_id == sessionScope.loginId}">
						<input type="button" value="삭제" onclick="replyDelete('${reply.reply_no}', '${reply.board_no}')">
					</c:if>
				</td>
			</tr>
			
			<!-- 리플 수정 폼이 생성될 공간: innerHTML을 사용해 태그를 채움 -->
			<tr>
				<td id="updateForm${reply.reply_no}" colspan="6">
					
				</td>
			</tr>
			
		</c:forEach>
	</table>
	
	<a href="/board/boardWriteForm">글쓰기</a> <br/>
	<a href="/board/boardList">게시판 이동</a> <br>
	<a href="/">홈으로 이동</a>
</body>
</html>