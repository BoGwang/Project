<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예매 목록</title>
</head>
<body>
	<h1>예매 목록</h1>
	<a href="<c:url value='/reservation/new.do'/>">예매 등록</a>
	<a href="<c:url value='/'/>">홈으로</a>
	<table border="1">
		<tr>
			<th>예매 번호</th>
			<th>영화 번호</th>
			<th>영화 스케줄 번호</th>
			<th>좌석 번호</th>
			<th>아이디</th>
		</tr>
		<c:forEach items="${ rlist }" var="item">
			<tr>
				<td><a href="<c:url value='/reservation/detail.do?rno=${ item.rno }'/>">${ item.rno }</a></td>
				<td>${ item.mno } </td>
				<td>${ item.sdno }</td>
				<td>${ item.seno }</td>
				<td>${ item.id }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>