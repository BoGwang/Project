<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>영화 목록</title>
</head>
<body>
	<h1>영화 목록</h1>
	<a href="<c:url value='/movie/movienew.do'/>">영화 등록</a>
	<a href="<c:url value='/'/>">홈으로</a>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>감독</th>
		</tr>
		<c:forEach items="${ mlist }" var="mlist">
			<tr>
				<td><a
					href="<c:url value='/movie/moviedetail.do?movieNo=${ mlist.movieNo }'/>">${ mlist.movieNo }</a></td>
				<td>${ mlist.movietitle }</td>
				<td>${ mlist.moviecontent }</td>
				<td>${ mlist.moviedirector }</td>
				</tr>
		</c:forEach>
	</table>
</body>
</html>