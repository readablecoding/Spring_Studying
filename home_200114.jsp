<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
<script type="text/javascript">
	function formCheck(){

		//태그로부터 값을 변수에 넣기
		var id = document.getElementById("id");
		var pw = document.getElementById("password");
		var age = document.getElementById("age");
		var name = document.getElementById("name");

		//오류 체크용
		//console.log(id.value.length);
		//console.log(id.values.length);

		
		//유효성 검사
		if(id.value.length > 8 || id.value.length < 4){
			alert("아이디는 4~8글자를 입력하세요.");
			id.focus(); //커서가 그곳에 가 있게 하는 것
			return false;
		}

		if(age.value == ""){
			alert("나이를 입력해 주세요.");
			return false;	
		}
		else if(isNaN(age.value)){
			alert("나이는 숫자만 입력해주세요.");
			return false;	
		}

		return true; //데이터가 제대로 전달되게끔 하는 것	
	}

</script>
</head>
<body>
	<h1>[회원가입]</h1>
	<form action="join" method="post" onsubmit="return formCheck();">
		ID : <input type="text" id="id" name="id" placeholder="아이디는 4~8글자"> <br/>
		PW : <input type="password" id="password" name="password" placeholder="비밀번호는 4~8글자"><br/>
		AGE : <input type="text" id="age" name="age" placeholder="나이는 숫자만"><br/>
		NAME : <input type ="text" id="name" name="name" placeholder="이름은 필수"><br/>
		<input type="submit" value="가입">
	</form>
	
<a href="JstlCore">JSTL-Core</a>
</body>
</html>
