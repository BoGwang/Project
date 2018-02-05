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
		<!-- 영화관 번호 -->
		<td>영화관 번호</td>
		<td colspan="3">${ thelist.movietitle }
	</tr>
	<tr>
		<!--영화관 이름 -->
		<td>영화관이름</td>
		<td colspan="3">${ thelist.moviecontent }</td>
	</tr>


</table>



<a href="<c:url value= '/schedule/schedulelist.do'/> ">스케줄 확인하기</a>
<a href="<c:url value='/movie/movielist.do'/>">영화 목록으로 이동</a>


</html>