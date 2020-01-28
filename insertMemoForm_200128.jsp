<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<form action="insertMemo" method="post" onsubmit="return formCheck();">
			<table>
				<tr>
					<td>
						내용					
					</td>
					<td>
						<input type="text" id="memo_content" name="memo_content">
					</td>
				</tr>
				<tr>
					<td>
						비밀번호
					</td>
					<td>
						<input type="password" id="memo_pw" name="memo_pw">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="등록">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>