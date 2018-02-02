<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>삭제 확인</title>
</head>
<body>
	<h1>삭제 확인</h1>
	<p>정말로 삭제하시겠습니까?</p>
	<form action="<c:url value='/movie/movieremove.do'/>" method="post">
		<input type="hidden" name="movieNo" value="${ movieNo }">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit" value="삭제하기">
	</form>
	<a href="<c:url value='/movie/movielist.do'/>">영화 목록으로 이동</a>
</body>
</html>