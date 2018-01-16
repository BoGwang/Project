<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>영화 수정하기</title>
</head>
<body>
	<h1>영화 수정하기</h1>
	<form action="<c:url value='/movie/modify.do'/>" method="post" enctype="multipart/form-data">
		<c:if test="${ param.action == 'error-password' }">
			<p>비밀번호가 일치하지 않습니다.</p>
		</c:if>
		<div>
			<label>번호 ${ item.mno }</label>
		</div>
		<div>
			<label>영화 제목 <input type="text" name="mtitle" value="${ item.mtitle }"></label>
		</div>
			<div>
			<label>감독 <input type="text" name="director" value="${ item.director }"></label>
		</div>
		<div>
			<label>내용 <textarea name="mcontent">${ item.mcontent }</textarea></label>
		</div>
		<div>
			<label>첨부파일 <input type="file" name="mposter" multiple="multiple"></label>
		</div>
		<div>
			<label>비밀번호 <input type="password" name="password"></label>
		</div>
		<input type="hidden" name="mno" value="${ item.mno }">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit">
		<input type="reset">
		<a href="<c:url value='/movie/list.do'/>">영화 목록으로 이동</a>
	</form>
</body>
</html>