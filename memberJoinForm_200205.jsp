<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[회원가입]</title>
<script type="text/javascript">
	function formCheck(){
		var member_id = document.getElementById("member_id");
		if(member_id.value == "" || member_id.value.length == 0){
			alert("아이디를 입력해주세요.");
			return false
		}
		return true;
	}

	function idCheck(){
		window.open("memberIdCheckForm", "newwin","left=200, top=200, width=500, height=300"); 
	}


</script>
</head>
<body>
	<h1>[회원 가입]</h1>	
	
	<form action="memberJoin" method="post" onsubmit="return formCheck();">
		ID : <input type="text" name="member_id" id="member_id" readonly="readonly">
		<input type="button" value="중복체크" onclick="idCheck()"><br/>
		PW : <input type="password" name="member_pw"><br/>
		이름 : <input type="text" name="member_nm"><br/>
		주소 : <input type="text" name="member_addr"><br/>
		<input type="submit" value="회원가입">
	</form>
	
</body>
</html>