<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예매 취소 확인</title>
</head>
<body>
	<h1>예매 취소 확인</h1>
	<form action="<c:url value='/reservation/remove.do'/>" method="post">
		<input type="hidden" name="rno" value="${ rno }">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit" value="취소하기">
	</form>
	<a href="<c:url value='/reservation/list.do'/>">예매 내역으로 이동</a>
</body>
</html>