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
	<a href="<c:url value='/schedule/theaterlist.do'/>">양화관 목록으로 이동</a>
	<a href="<c:url value='/'/>">홈으로</a>
	<table border="1">
		<tr>
			<th>영화관 번호</th>
			<th>영화관 이름</th>
					</tr>
		<c:forEach items="${ thelist }" var="theater">
			<tr>
				<td><a
					href="<c:url value='/schedule/theaterdetail.do?theNo=${ theater.theNo }'/>">${ theater.theNo }</a></td>
				<td>${ theater.thename }</td>
							</tr>
		</c:forEach>
	</table>
</body>
</html>