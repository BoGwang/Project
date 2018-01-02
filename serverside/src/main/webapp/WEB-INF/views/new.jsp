<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form action="<c:url value='/board/new.do'/>" method="post" enctype="multipart/form-data">
		<div>
			<label>작성자 <span>${ email }</span></label>
		</div>
		<div>
			<label>제목 <input type="text" name="title"></label>
		</div>
		<div>
			<label>내용 <textarea name="content"></textarea></label>
		</div>
		<div>
			<label>첨부파일 <input type="file" name="attachment" multiple="multiple"></label>
		</div>
		<input type="hidden" name="id" value="${ id }">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit">
		<input type="reset">
		<a href="<c:url value='/board/list.do'/>">게시판 목록으로 이동</a>
	</form>
</body>
</html>