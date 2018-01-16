<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>영화 상세보기</title>
</head>
<body>
	<h1>영화 상세보기</h1>
	<dl>
		<dt>번호</dt>
		<dd>${ item.mno }</dd>
		<dt>제목</dt>
		<dd>${ item.mtitle }</dd>
		<dt>감독</dt>
		<dd>${ item.director }</dd>
		<dt>내용</dt>
		<dd>${ item.mcontent }</dd>
		<c:if test="${ !empty filename }">
			<dt>영화포스터</dt>
			<dd><a href="<c:url value='/movie/download.do?filename=${ item.mposter }'/>">${ filename }</a></dd>
		</c:if>
	</dl>
	<a href="<c:url value='/movie/list.do'/>">영화 목록으로 이동</a>
	<a href="<c:url value='/movie/modify.do?mno=${ item.mno }'/>">영화 수정하기</a>
	<a href="<c:url value='/movie/remove.do?mno=${ item.mno }'/>">영화 삭제하기</a>
</body>
</html>