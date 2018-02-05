<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>티켓 상세보기</title>
</head>
<body>
	<h1>티켓 상세보기</h1>
	<dl>
		<dt>티켓번호</dt>
		<dd>${ ticket.ticNo }</dd>
		<dt>회원아이디</dt>
		<dd>${ ticket.userInfo.id }</dd>
		<dt>스케줄 번호</dt>
		<dd>${ ticket.ticNo }</dd>
		<dt>좌석 번호</dt>
		<dd>${ ticket.ticseatno }</dd>
	</dl>
	<a href="<c:url value='/schedule/ticketist.do'/>">티켓 목록으로 이동</a>
</body>
</html>