<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
	<form action="UM" method="post">
		<label>
			조회할 이메일 : <input type="email" name="email"><br>
		</label>
		<label>
			수정할 이름 : <input type="text" name="name"><br>
		</label>
		<label>
			변경할 비밀번호 : <input type="password" name="password"><br>
		</label>
		<input type="submit" value="변경">
	</form>
</body>
</html>