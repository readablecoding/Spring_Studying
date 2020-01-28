<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>메모</title>
	<link rel="stylesheet" href="resources/css/style.css">
	<script type="text/javascript">
		function selectMemoList(){
			location.href="selectMemoList";
		}
	</script>
</head>
<body>
	<div class="wrap">
		<h1>
			[나만의 메모장]  
		</h1>
		<input type="button" value="메모확인" onclick="selectMemoList()">
	</div>
</body>
</html>
