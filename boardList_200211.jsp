<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[글 목록]</title>
<script type="text/javascript">
	function boardWriteForm(){
		location.href="boardWriteForm";	 
	}
	//또는 boardWriteForm로 적기
	
	function boardReadForm(board_no){
		location.href="boardReadForm?board_no="+board_no;
	}

	function paging(page){
		var pagingForm = document.getElementById("pagingForm");
		var currentpage = document.getElementById("currenPage");

		currentPage.value = page;
		pagingForm.submit();		
	}
	function boardList(){
		location.href="boardList";
	}
</script>
</head>
<body>
		<table>
			<tr>
				<th>글 번호</th>	
				<th>글 제목</th>
				<th>글쓴이</th>
				<th>조회수</th>
				<th>등록일</th>
			</tr>
			
			<c:forEach var="item" items="${list}" varStatus="status">
				<tr>				
					<td>${status.count}</td>		
					<td>
						<a href="javascript:boardReadForm('${item.BOARD_NO}')">${item.BOARD_TITLE}</a>
					</td>
					<td>${item.MEMBER_NM}</td>
					<td>${item.BOARD_HITS}</td>
					<td>${item.BOARD_INDATE}</td>	
				</tr>
			</c:forEach>
		</table>	
		
		<!-- 페이징 처리 -->
		<a href="javascript:paging('${navi.currentPage - navi.pagePerGroup}')">◁◁</a>
		<a href="javascript:paging('${navi.currentPage - 1}')">◀</a>
		<c:forEach var="counter" begin="${navi.startPageGroup}" end="${navi.endPageGroup}">
			<c:choose>
				<c:when test="${counter == navi.currentPage}">
					<b><a href="javascript:paging('${counter}')">${counter}</a></b>
				</c:when>
				<c:otherwise>
					<small><a href="javascript:paging('${counter}')">${counter}</a></small>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<a href="javascript:paging('${navi.currentPage + 1}')">▶</a>
		<a href="javascript:paging('${navi.currentPage + navi.pagePerGroup}')">▷▷</a>
			
		<!-- 검색 처리 -->
		<!-- 페이징 or 검색 요청시 전달할 폼 -->
		<form id="pagingForm" action="boardList" method="get">
			<input type="hidden" id="currentPage" name="currentPage">
			제목 : <input type="text" name="searchText" value="${searchText}">
			<input type ="button" value="검색하기" onclick="paging(1)">
		</form>
		
	<input type="button" value="게시판 첫 페이지로 이동" onclick="boardList()"><br/> 			
	<input type="button" value="글 등록" onclick="boardWriteForm()"><br/>
	<a href="/">홈으로 이동</a>
</body>
</html>