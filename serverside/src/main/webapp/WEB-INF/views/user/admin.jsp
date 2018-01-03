<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
</head>
<body>
	<h1>관리자 페이지</h1>
	<div>
		<span>${ email }</span>
		<span>님 관리자 페이지에 오신것을 환영합니다.</span>
		<a href="<c:url value='/logout'/>">로그아웃</a>
		<a href = "<c:url value = '/'/>">홈으로</a>
	</div>
</body>
</html>