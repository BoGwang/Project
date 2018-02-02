<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>영화쓰기</title>
</head>
<body>
	<h1>영화쓰기</h1>
	<form action="<c:url value='/movie/movienew.do'/>" method="post" enctype="multipart/form-data">
		<div>
			<label>제목 <input type="text" name="movietitle"></label>
		</div>
		<div>
			<label>감독 <textarea name="moviedirector"></textarea></label>
		</div>
		<div>
			<label>내용 <textarea name="moviecontent"></textarea></label>
		</div>
		<div>
			<label>첨부파일 <input type="file" name="movieposter" multiple="multiple"></label>
		</div>
		<input type="hidden" name="id" value="${ id }">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit">
		<input type="reset">
		<a href="<c:url value='/movie/movielist.do'/>">영화 목록으로 이동</a>
	</form>
</body>
</html>