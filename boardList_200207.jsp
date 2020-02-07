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
	<input type="button" value="글 등록" onclick="boardWriteForm()"><br/>
	<a href="/">홈으로 이동</a>
</body>
</html>