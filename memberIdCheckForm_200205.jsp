<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ID 중복 체크]</title>
<script type="text/javascript">
	function useId(checkId){
		//파라미터 전달 받은 아이디를 부모창의 ID칸에 입력
		opener.document.getElementById("member_id").value = checkId;
		//자식창(본인)을 닫음.
		this.close();
	}
</script>
</head>
<body>
	
	<form action="memberIdCheck" method="post">
		ID : <input type="text" name="checkId" id="checkId"> <br/>
		<input type="submit" value="중복체크">
	</form>
	
	<c:if test="${checkFlag}">
		<c:choose>
			<c:when test="${member == null}">
				${checkId}는 사용할 수 있습니다.
				<input type="button" value="적용하기" onclick="useId('${checkId}')">
			</c:when>
			<c:otherwise>
				${checkId}는 사용할 수 없습니다.
			</c:otherwise>
		</c:choose>
	</c:if>
	
</body>
</html>