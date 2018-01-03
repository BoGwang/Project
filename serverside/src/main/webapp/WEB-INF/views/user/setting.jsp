<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 정보 수정</h1>
	<a href="<c:url value='/user/modify'/>">회원 수정</a>
	<a href="<c:url value='/user/delete'/>">회원 탈퇴</a>
	<a href="<c:url value='/'/>">홈으로</a>
</body>
</html>