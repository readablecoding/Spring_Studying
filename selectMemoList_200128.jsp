<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>메모</title>
	<link rel="stylesheet" href="resources/css/style.css">
	<script type="text/javascript" src="resources/js/script.js"></script>
</head>
<body>
	<div class="wrap">
		<h1>
			[나만의 메모장]  
		</h1>
		
		<c:choose>
			<c:when test="${not empty list}">
				<table>
					<tr>
						<th>번호</th>
						<th>내용</th>
						<th>등록일</th>
						<th>비밀번호</th>
						<th></th>
					</tr>
					<c:forEach items="${list }" var="item">
						<tr>
							<td>${item.memo_no }</td>
							<td>${item.memo_content }</td>
							<td>${item.memo_indate }</td>
							<td><input type="password" id="pwCheck${item.memo_no}"></td>
							<td><input type="button" value="삭제" onclick="delMemo('${item.memo_no}')"></td>
						</tr>
					</c:forEach>
				</table>
				
				<form action="deleteMemo" id="deleteForm" method="post">
					<input type="hidden" id="memo_no" name="memo_no">
					<input type="hidden" id="memo_pw" name="memo_pw">
				</form>
			</c:when>
			<c:otherwise>
				<div class="nomemo">
					등록된 메모가 없습니다.
				</div>
			</c:otherwise>
		</c:choose>
		<input type="button" value="메모등록" onclick="insertMemoForm()">
		
	</div>
</body>
</html>