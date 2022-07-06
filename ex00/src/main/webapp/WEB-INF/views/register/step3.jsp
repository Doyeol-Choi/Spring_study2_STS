<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register" /></title>
</head>
<body>
	<%-- <p><strong>${registerRequest.name}님! </strong>회원 가입을 완료했습니다.</p> --%>
	<p><spring:message code="register.done" arguments="${formData.name}" /></p>
	<p><a href="<c:url value='/main'/>"><spring:message code="go.main" /></a></p>
</body>
</html>