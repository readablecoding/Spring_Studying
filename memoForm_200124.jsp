<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/css/setting.css" rel="stylesheet">
<title>[메모 등록]</title>
<script type="text/javascript">
	function formCheck(){
		
		var memo_content = document.getElementById("memo_content");
		var memo_pw = document.getElementById("memo_pw");

		if(memo_content.value.length == 0){
			alert("내용을 입력해주세요.");
			return false;
		}
		if(memo_pw.value.length < 3 || memo_pw.value.lengh > 8){
			alert("비밀번호는 3~8글자 사이로 입력해주세요.");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div id="center">
	<h1>[나만의 메모장]</h1>
	
	<form id="memoForm" action="insertMemo" method="post" onsubmit="return formCheck()">
	 	<table>
			<tr>
				<td>내용</td>
				<td><input type="text" id="memo_content" name="memo_content" placeholder="내용 입력"></td>	
			<tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="memo_pw" name="memo_pw" placeholder="3 ~8 글자로 입력해주세요."></td>
			</tr>
			<tr>
				<td><input type="submit" value="등록"></td>
			</tr>
		</table>	
	</form>
</div>
</body>
</html>