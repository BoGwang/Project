<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>영화 수정하기</title>
</head>
<body>
	<h1>영화 수정하기</h1>
	<form action="<c:url value='/movie/moviemodify.do'/>" method="post"
		enctype="multipart/form-data">
		<div>
			<label>번호 ${ item.movieNo }</label>
		</div>
		<div>
			<label>영화 제목 <input type="text" name="movietitle"
				value="${ item.movietitle }"></label>
		</div>
		<div>
			<label>내용 <textarea name="mcontent">${ item.mcontent }</textarea></label>
		</div>
		<div>
			<label>감독 <input type="text" name="moviedirector"
				value="${ item.moviedirector }"></label>
		</div>
		<div>
			<label>첨부파일 <input type="file" name="movieposter"
				multiple="multiple"></label>
		</div>
		<input type="hidden" name="movieNo" value="${ item.movieNo }"> <input
			type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit"> <input type="reset"> <a
			href="<c:url value='/movie/movielist.do'/>">영화 목록으로 이동</a>
	</form>
</body>
</html>