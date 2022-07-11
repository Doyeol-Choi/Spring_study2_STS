<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 조사</title>
</head>
<body>
	<h2>설문 조사</h2>
	<c:forEach items="${qList}" var="q" varStatus="s">
		<p>
			${s.index+1}.${q.title}<br>
			<c:if test="${q.choice}">
				<c:forEach items="${q.option}" var="op">
					<label>
						<input type="radio" name="responses[${s.index}]" value="${op}"> ${op}
					</label>
				</c:forEach>
			</c:if>
			<c:if test="${!q.choice}">
				<label>
					<input type="text" name="responses[${s.index}]">
				</label>
			</c:if>
		</p>
	</c:forEach>
	<p>
		<label> 응답자 위치 : 
			<input type="text" name="res.location">
		</label>
	</p>
	<p>
		<label> 응답자 나이 : 
			<input type="text" name="res.age">
		</label>
	</p>
	<input type="submit" value="전송">
	
	
	<!-- <form method="post">
		<p>
			1. 당신의 역할은? <br>
			<label> <input type="radio" name="responses[0]" value="프론트"> 프론트 개발자 </label>
			<label> <input type="radio" name="responses[0]" value="백엔드"> 백엔드 개발자 </label>
			<label> <input type="radio" name="responses[0]" value="풀스택"> 풀스택 개발자 </label>
		</p>
		<p>
			2. 가장 많이 사용하는 개발 도구는? <br>
			<label> <input type="radio" name="responses[1]" value="Eclipse"> Eclipse </label>
			<label> <input type="radio" name="responses[1]" value="IntelliJ"> IntelliJ </label>
			<label> <input type="radio" name="responses[1]" value="NetBeans"> NetBeans </label>
		</p>
		<p>
			3. 하고 싶은 말 <br>
			<input type="text" name="responses[2]">
		</p>
		<p>
			<label> 응답자 위치 : 
				<input type="text" name="res.location">
			</label>
		</p>
		<p>
			<label> 응답자 나이 : 
				<input type="text" name="res.age">
			</label>
		</p>
		<input type="submit" value="전송">
	</form> -->
</body>
</html>