<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 목록</title>
</head>
<body>
	<h1>글 목록</h1>
	<a href="<c:url value='/board/new.do'/>">글쓰기</a>
	<a href="<c:url value='/'/>">홈으로</a>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach items="${ list }" var="item">
			<tr>
				<td><a href="<c:url value='/board/detail.do?no=${ item.no }'/>">${ item.no }</a></td>
				<td>${ item.title }</td>
				<td>${ item.userInfo.name }</td>
				<td>${ item.regdate }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>