<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[게시판]</title>
<script type="text/javascript">
	function noticeReadForm(notice_no){
		location.href = "noticeReadForm?notice_no=" + notice_no;
	}

	function movePage(page){
		var pageForm = document.getElementById("pageForm");
		var CurrentPage = document.getElementById("page");
		CurrentPage.value = page;
		pageForm.submit();	
	}

</script>
</head>
<body>
	<h1>게시글 목록</h1>
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성일</th>	
		</tr>
		<c:forEach var="notice" items="${list}">
			<tr>
				<td>${notice.notice_no}</td>
				<td>
					<a href="javascript:noticeReadForm('${notice.notice_no}')">${notice.notice_title}</a>
				</td>
				<td>${notice.notice_name}</td>
				<td>${notice.notice_hits}</td>
				<td>${notice.notice_indate}</td>
			</tr>
		</c:forEach>
	</table>
	

	<div>
		<a href="javascript:movePage('${navi.currentPage - navi.pagePerGroup}')">◁◁</a>
		<a href="javascript:movePage('${navi.currentPage - 1}')">◀ </a>
		<c:forEach var="counter" begin="${navi.startPageGroup}" end="${navi.endPageGroup}">
			<c:choose>
				<c:when test="${counter == navi.currentPage}">
					<b><a href="javascript:movePage('${counter}')">${counter}</a></b>
				</c:when>
				<c:otherwise>
					<small><a href="javascript:movePage('${counter}')">${counter}</a></small>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<a href="javascript:movePage('${navi.currentPage + 1}')">▶</a>
		<a href="javascript:movePage('${navi.currentPage + navi.pagePerGroup}')">▷▷</a>
	</div>
	
	<form action="noticeList" method="get" id="pageForm">
		<input type="hidden" id="page" name="page">
	</form>
	
	<a href="/notice/noticeInsertForm">글 등록</a><br/>
	<a href="/">메인으로 이동</a><br/>
	
</body>
</html>