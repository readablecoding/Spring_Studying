<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ jstlCore ]</title>
</head>
<body>
	<h2>* set 태그 - attribute 저장</h2>
	<c:set var="n" value="100"/> 
	n = ${n}<br/>
	
	<h2>* remove 태그 - attribute 삭제</h2>
	<c:remove var="n"/>
	n = ${n}<br/>
	
	<h2>* if - 조건 검사</h2>
	<c:if test="${str == 'abc'}">
		str의 값은 'abc'입니다.<br/>
	</c:if>

	<c:if test="${number > 0}">
		number의 값은 0보다 크다. <br/>
	</c:if>
	
	<!-- if ~ else문 -->
	<h2>* choose - 다중 분기</h2>
	<c:choose>
		<c:when test="${number == 1}">
			number의 값은 1입니다. <br/>
		</c:when>
		<c:when test="${number == 2}">
			number의 값은 2입니다. <br/>
		</c:when>
		<c:otherwise>
			number의 값은 그 외의 값이다 <br/>
		</c:otherwise>
	</c:choose>
	
	<h2>* forEach - 반복</h2>
	
	<c:forEach var = "cnt" begin ="1" end ="5">
		반복 ${cnt}
	</c:forEach>
	<br/>
	
	<!-- end 값은 컬렉션 사이즈의 -1까지, ArrayList의 값 가져올 때 get메서드, 원하는 번호에서 가져오기-->
	<c:forEach	var="i" begin = "0" end ="${list.size() - 1}">
		${list.get(i)}
	</c:forEach>
	<br/>
	
	<!-- java의 forEach문과 비슷한 유형, items를 사용, items에 ArrayList, Map등이 올 수 있다. -->
	<c:forEach var ="value" items ="${list}">
		${value}	
	</c:forEach>
	<br/>
	
	<!-- 특수문자 자체를 출력할 때 -->
	<h2>* out - 데이터 출력(특수문자 포함)</h2>
	EL로 출력: ${data} <br/>
	<c:out value="${data}" /> <br/>
	
</body>
</html>