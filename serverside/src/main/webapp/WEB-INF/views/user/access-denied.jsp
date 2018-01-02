<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>접근 불가 페이지</title>
</head>
<body>
	<h1>접근 불가 페이지</h1>
	<div>
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
			<span>${ email }</span>
			<span>님 은 접근 불가한 페이지입니다.</span>
			<a href="<c:url value = '/logout'/>">로그아웃</a>
		</sec:authorize>
		<sec:authorize access="!hasRole('ADMIN') and !hasRole('USER')">
			<span>해당 페이지 접근 가능한 아이디로 로그인 하세요.</span>
			<a href="<c:url value = '/login'/>">로그인</a>
		</sec:authorize>
	</div>
</body>
</html>