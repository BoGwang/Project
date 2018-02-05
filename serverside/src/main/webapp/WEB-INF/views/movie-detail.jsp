<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>영화 상세보기</title>
</head>

<h1>영화 정보</h1>
<table>
	<tr>
		<!-- 제목 -->
		<td>제목</td>
		<td colspan="3">${ mlist.movietitle }
	</tr>
	<tr>
		<!-- 줄거리 -->
		<td>줄거리</td>
		<td colspan="3">${ mlist.moviecontent }</td>
	</tr>
	<tr>
		<!-- 영화 감독 -->
		<td>감독</td>
		<td>${ mlist.moviedirector }</td>
	</tr>
	<c:if test="${ !empty filename }">
		<dt>영화포스터</dt>
		<dd>
			<a
				href="<c:url value='/movie/download.do?filename=${ mlist.movieposter }'/>">${ filename }</a>
		</dd>
	</c:if>


</table>



<a href="<c:url value= '/schedule/schedulelist.do'/> ">스케줄 확인하기</a>
<a href="<c:url value='/movie/movielist.do'/>">영화 목록으로 이동</a>
	<a href="<c:url value='/movie/movieremove.do?movieNo=${ mlist.movieNo }'/>">삭제하기</a>
<a 	href="<c:url value='/movie/moviemodify.do?movieNo=${ mlist.movieNo }'/>">영화
	수정하기</a>

</html>