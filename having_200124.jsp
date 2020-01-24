<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ 메모 확인 ]</title>
<link href="resources/css/setting.css" rel="stylesheet">
<script type="text/javascript">
	function formDelete(pk){
		
		console.log("delete 함수 진입"); 
		var pwCheck = document.getElementById("pwCheck" + pk);
		if(pwCheck.value.length == 0){
			alert("비밀번호를 입력해 주세요.");
			return false;
		}	

		console.log("히든폼 돌입");
		var hiddenForm = document.getElementById("hiddenForm");
		var memo_no = document.getElementById("memo_no");
		var memo_pw = document.getElementById("memo_pw");
		
		memo_no.value = pk;
		memo_pw.value = pwCheck.value;

		console.log("값 넣기 성공");
		hiddenForm.submit();
	}


</script>

</head>
<body>
	<h1>[나만의 메모장]</h1>
		<form>
			<table border="1">
				<tr>
					<th>번호</th>
					<th>내용</th>
					<th>등록일</th>
					<th>비밀번호</th>
					<th>삭제</th>		
				</tr>
			<c:forEach var="list" items="${list}">
				<tr>
					<td>${list.memo_no}</td>
					<td>${list.memo_content}</td>
					<td>${list.memo_indate}</td>
					<td><input type="password" id="pwCheck${list.memo_no}"></td>
					<td><input type="button" value="삭제" onclick="formDelete('${list.memo_no}')"></td>
				</tr>
			</c:forEach>					
			</table>	
		</form>
			<input type="button" id="button" value="메모 등록" onclick="location.href='memoForm'">	
				
		<form id="hiddenForm" action="deleteMemo" method="post">
			<input type="hidden" id="memo_no" name="memo_no"> 
			<input type="hidden" id="memo_pw" name="memo_pw">
		</form>
		
		
</body>
</html>