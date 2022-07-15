<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<a href="<c:url value='http://localhost:8085/mybatis/FS' />">[회원 목록 조회]</a><br>
	<a href="<c:url value='http://localhost:8085/mybatis/MC' />">[회원 수 조회]</a><br>
	<a href="<c:url value='http://localhost:8085/mybatis/MSE' />">[회원 이메일로 조회]</a><br>
	<br><br>
	<a href="<c:url value='http://localhost:8085/mybatis/AM' />">[회원 추가]</a><br>
	<a href="<c:url value='http://localhost:8085/mybatis/UM' />">[회원 변경]</a><br>
	<a href="<c:url value='http://localhost:8085/mybatis/DM' />">[회원 삭제]</a><br>
	<br><br>
</body>
</html>