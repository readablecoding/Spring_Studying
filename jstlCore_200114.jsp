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
	
	<h2>* remove 태그 - attribut 삭제</h2>
	<c:remove var="n"/>
	n = ${n}<br/>
</body>
</html>