<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<form action="AM" method="post">
		<label>
			이메일 : <input type="email" name="email"><br>
		</label>
		<label>
			이름 : <input type="text" name="name"><br>
		</label>
		<label>
			비밀번호 : <input type="password" name="password"><br>
		</label>
		<input type="submit" value="추가">
	</form>
</body>
</html>