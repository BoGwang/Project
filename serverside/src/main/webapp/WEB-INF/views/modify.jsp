<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수정하기</title>
</head>
<body>
	<h1>수정하기</h1>
	<form action="<c:url value='/board/modify.do'/>" method="post" enctype="multipart/form-data">
		<c:if test="${ param.action == 'error-password' }">
			<p>비밀번호가 일치하지 않습니다.</p>
		</c:if>
		<div>
			<label>번호 ${ item.no }</label>
		</div>
		<div>
			<label>작성자 ${ item.userInfo.name }</label>
		</div>
		<div>
			<label>제목 <input type="text" name="title" value="${ item.title }"></label>
		</div>
		<div>
			<label>내용 <textarea name="content">${ item.content }</textarea></label>
		</div>
		<div>
			<label>첨부파일 <input type="file" name="attachment" multiple="multiple"></label>
		</div>
		<div>
			<label>비밀번호 <input type="password" name="password"></label>
		</div>
		<input type="hidden" name="no" value="${ item.no }">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit">
		<input type="reset">
		<a href="<c:url value='/board/list.do'/>">게시판 목록으로 이동</a>
	</form>
</body>
</html>