<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>formData1 화면</h1>
	
	<form action="formDataVo" method="post">
		id<input type="text" name="str"><br/>
		password<input type="text" name="password"><br/>
		hobby<br/>
			<input type="checkbox" name="hobby" value="축구">축구<br/>
			<input type="checkbox" name="hobby" value="야구">야구<br/>
			<input type="checkbox" name="hobby" value="농구">농구<br/>	
		<input type="submit" value="전송">	
	</form>
</body>
</html>