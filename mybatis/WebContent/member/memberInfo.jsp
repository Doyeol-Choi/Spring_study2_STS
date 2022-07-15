<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일로 조회된 회원 정보</title>
</head>
<body>
	아이디 : ${mVo.id} <br>
	이름 : ${mVo.name} <br>
	이메일 : ${mVo.email} <br>
	비밀번호 : ${mVo.password} <br>
	가입일 : ${mVo.registerDate} <br>
</body>
</html>