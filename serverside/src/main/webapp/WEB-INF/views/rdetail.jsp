<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예매 상세보기</title>
</head>
<body>
	<h1>예매 상세보기</h1>
	<dl>
		<dt>예매 번호</dt>
		<dd>${ item.rno }</dd>
		<dt>영화 번호</dt>
		<dd>${ item.mno }</dd>
		<dt>영화 스케줄 번호</dt>
		<dd>${ item.sdno }</dd>
		<dt>좌석 번호</dt>
		<dd>${ item.seno }</dd>
		<dt>아이디</dt>
		<dd>${ item.id }</dd>
	</dl>
	<a href="<c:url value='/reservation/list.do'/>">예매 목록으로 이동</a>
	<a href="<c:url value='/reservation/remove.do?rno=${ item.rno }'/>">예매 취소하기</a>
</body>
</html>