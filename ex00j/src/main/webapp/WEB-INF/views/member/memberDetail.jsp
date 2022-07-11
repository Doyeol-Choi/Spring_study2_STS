<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member" /></title>
</head>
<body>
	<p><spring:message code="member.id" /> : ${member.id}</p>
	<p><spring:message code="email" /> : ${member.email}</p>
	<p><spring:message code="name" /> : ${member.name}</p>
	<p><spring:message code="register.info" /> : <fmt:formatDate value="${member.registerDate}" pattern="yyyy-MM-dd HH:mm" /></p>
	<br>
	<p>
		<a href="<c:url value='/member/list' />">[날짜별 회원 정보 보기]</a>
	</p>
</body>
</html>