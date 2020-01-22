<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>

<script type="text/javascript">
//수정폼의 유효성검사 함수
function formCheck() {
	var id = document.getElementById('custid');
	var pw = document.getElementById('password');
	var pw2 = document.getElementById('password2');
	var nm = document.getElementById('name');
	
	if (id.value.length < 3 || id.value.length > 10) {
		alert('ID는 3~10자로 입력하세요.');
		return false;
	}
	if (pw.value.length < 3 || pw.value.length > 10) {
		alert("비밀번호는 3~10자로 입력하세요.");
		return false;
	}
	if (pw.value != pw2.value) {
		alert('비밀번호를 정확하게 다시 입력하세요.');
		return false;
	}
	if (nm.value == '') {
		alert('이름을 입력하세요.');
		return false;
	}
	return true;
}
</script>
</head>
<body>

<h1>[ 회원 정보 수정 ]</h1>

<form id="updateForm" action="updateCustomer" method="post" onsubmit="return formCheck()">
<table>
<tr>
	<td>고객 ID</td>
	<td><input type="text" name="custid" id="custid" placeholder="ID를 3~10자로 입력" value="${customer.custid}" readonly="readonly"/></td>
</tr>
<tr>
	<td>비밀번호</td>
	<td><input type="password" name="password" id="password" placeholder="Password를 3~10자로 입력" /></td>
</tr>
<tr>
	<td>비밀번호 확인</td>
	<td><input type="password" name="password2" id="password2" placeholder="비밀번호를 다시 한번 입력" /></td>
</tr>
<tr>
	<td>이름</td>
	<td><input type="text" name="name" id="name" placeholder="이름 입력" value="${customer.name}"/></td>
</tr>
<tr>
	<td>E-mail</td>
	<td><input type="text" name="email" placeholder="E-mail 주소 입력" value="${customer.email}"/></td>
</tr>
<tr>
	<td>회원구분</td>
	<td>
		<c:choose>
			<c:when test="${customer.division == 'personal'}">
				<input type="radio" name="division" value="personal" checked /> 개인 
				<input type="radio" name="division" value="company" /> 법인
			</c:when>
			<c:otherwise>
				<input type="radio" name="division" value="personal"  /> 개인 
				<input type="radio" name="division" value="company" checked/> 법인
			</c:otherwise>
		</c:choose>
	</td>
</tr>
<tr>
	<td>식별번호</td>
	<td><input type="text" name="idno" placeholder="개인:주민번호 /법인:사업자번호" value="${customer.idno}" readonly="readonly"/></td>
</tr>
<tr>
	<td>주소</td>
	<td><input type="text" name="address" placeholder="주소" value="${customer.address}"/></td>
</tr>
<tr>
	<th colspan="2">
		<input type="submit" value="가입">
		<input type="reset" value="다시 쓰기">
	</th>
</tr>
</table>
</form>

</body>
</html>