<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[로그인 폼]</title>
<script type="text/javascript">
	function formCheck(){
		var member_id = document.getElmentById("member_id");
		var member_pw = document.getElementById("member_pw");
		if(member_id.value == "" || member_id.value.length == 0){
			alert("아이디를 입력해주세요.");
			return false;
		}
		if(member_pw.value == "" || member_pw.value.length == 0){
			alert("비밀번호를 입력해주세요.");
			return false;
		}	
		return true;
	}
</script>
</head>
<body>
	<h1>[로그인]</h1>
		
	<form action="memberLogin" method="post" onsubmit="return formCheck();">
		ID : <input type="text" id="member_id" name="member_id" value="${rememberId}"><br/>
		PW : <input type="password" id="member_pw" name="member_pw"><br/>
		<input type="checkbox" name="remember" value="1">아이디 기억하기<br/>
		<input type="submit" value="로그인">
	</form>
	
	<c:if test="${errMsg != null}">
		${errMsg}
	</c:if>
</body>
</html>