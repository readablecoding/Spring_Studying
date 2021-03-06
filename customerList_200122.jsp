<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ 회원 목록 ]</title>
<script type="text/javascript">

	function deleteCustomer(pk){	//받아온 데이터 pk -> 아이디가 프라이머리 키

		//컨트롤러의 삭제기능 요청주소로 연결
		//쿼리 스트링: 요청을 보낼 때 요청주소에 데이터를 연결하여 보내는 방식
		
		//location.href = "deleteCustomer?custid=" + pk;	//문자열 연결하기 위해 + 사용
		//deleteCustomer?custid=comp&password=1234&email=abcd
		//deleteCustomer?custid=admin
		//deleteCustomer?custid=admin2

		//?전이 요청 주소이고, ?이후에는 이름과 데이터가 나온다. 두번째 데이터부터는 &를 붙인다.
		//주소에 보이는 이유는 get방식으로 쿼리스트링을 보내기 때문이다.
		//요청주소?이름=값&이름=값&이름=값.........
		//location.href = ""을 통해  value가 맞는 Controller로 이동하게 된다.

		var pwCheck = document.getElementById("pwCheck" + pk); //입력한 비밀번호의 칸의 값에 아이디를 붙임

		if(pwCheck.value.length == 0){
			alert("비밀번호를 입력해주세요.");
			return false;	
		}

		var hiddenForm = document.getElementById("hiddenForm"); //element 다 가져오기
		var password = document.getElementById("password"); //히든 폼의 id가 password인 것의 요소
		var custid = document.getElementById("custid");
		
		password.value = pwCheck.value;	//입력한 비밀번호를 히든폼의 비밀번호에 넣는 것
		custid.value = pk;	//가져온 아이디를 히든폼의 아이디에 넣는다.

		//폼을 submit 시키기
		hiddenForm.submit(); 
		//submit이 발생함 submit되면 action 지정된 곳(CustomerController.java의 value = "deleteCustomer2")으로 이동
	}
</script>

</head>
<body>
	<h1>[ 회원 목록] </h1>
	<table border="1">
		<tr>	
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>구분</th>
			<th>주소</th>
			<th>비밀번호</th>
			<th>버튼</th>
		</tr>
		<c:forEach var="cust" items="${list}" >
		<tr>
			<td>
				<a href ="updateCustomerForm?custid=${cust.custid}">${cust.custid}</a> 
			</td>
			<td>${cust.name}</td>
			<td>${cust.email}</td>
			<td>
				<c:choose>
					<c:when test="${cust.division == 'personal'}">
						개인
					</c:when>
					<c:otherwise>
						법인
					</c:otherwise>
				</c:choose>
			</td>
			<td>${cust.address}</td>	
			<td>
				<input type="password" id="pwCheck${cust.custid}">
			</td>
			<td>
				<input type="button" value="삭제" onclick="deleteCustomer('${cust.custid}')">
			</td>	
		</tr>
		</c:forEach>	
	</table>
	<br/>
	
	<!-- 히든 폼 시작 -->
	<form action="deleteCustomer2" method="post" id="hiddenForm">
		<input type="hidden" id="password" name="password">
		<input type="hidden" id="custid" name="custid">			
	</form>
	<!-- 히든 폼 종료-->
	
	
	<a href="/">home으로 이동</a>
</body>
</html>