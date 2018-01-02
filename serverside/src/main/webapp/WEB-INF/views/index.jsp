<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>초기 화면</title>
</head>
<body>
	<h1>초기 화면</h1>
	<sec:authorize access="!hasRole('ADMIN') and !hasRole('USER')">
		<a href="<c:url value='/login'/>">로그인하기</a>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
		<a href="<c:url value='/board/list.do'/>">게시판으로 이동</a>
		<a href="<c:url value='/user/setting'/>">회원정보수정</a>
		<div>
			<label>이메일: <span>${ userInfo.email }</span></label>
			<label>이름: <span>${ userInfo.name }</span></label>
			<img src="<c:url value='/avatar/${ userInfo.avatar }'/>">
			<div>권한</div>
			<c:forEach var="item" items="${ userInfo.userTypes }">
				<div>${ item.type }</div>
			</c:forEach>
		</div>		
	</sec:authorize>
</body>
</html>