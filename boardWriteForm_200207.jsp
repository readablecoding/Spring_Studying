<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[글 등록]</title>
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
</script>
</head>
<body>
	<form action="boardWrite" method="post" onsubmit="return formCheck();" enctype="multipart/form-data"> 
		글 제목 : <input type="text" id="board_title" name="board_title"><br/>
		글 내용 : <textarea id="board_content" name="board_content"></textarea><br/>
		첨부파일 : <input type="file" name="upload"><br/>
		<input type="submit" value="글 등록">
	</form>	
	<a href="/board/boardList">게시판 이동</a> <br/>
	<a href="/">홈으로 이동</a>
</body>
</html>