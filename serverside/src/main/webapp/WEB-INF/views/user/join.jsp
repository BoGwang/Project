<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 가입</h1>
	<form action='<c:url value='/join'/>' method="post" enctype="multipart/form-data">
		<label>이메일 <input type="email" name="email"></label>
		<label>비밀번호 <input type="password" name="password"></label>
		<label>이름 <input type="text" name="name"></label>
		<label>첨부 파일 <input type="file" name="avatar" multiple="multiple"></label>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit" value="회원가입">
	</form>
</body>
</html>