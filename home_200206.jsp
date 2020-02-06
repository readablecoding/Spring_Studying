<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sample Project</title>
</head>
<body>
<h1>[ Spring과 Mybatis를 이용한 Web Application 예제]</h1>
<ul>
	<c:choose>
		<c:when test="${sessionScope.loginId != null}">
			<li><a href="">게시판 이동</a></li>
			<li><a href="/member/memberLogout">로그아웃</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="/member/memberJoinForm">회원가입 폼 이동</a></li>
			<li><a href="/member/memberLoginForm">로그인 폼 이동</a></li>
		</c:otherwise>
	</c:choose>
</ul>
</body>
</html>