<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/jquery.js"></script>
<script type="text/javascript">
function goReservePage() {
	window.location = "<%=request.getContextPath()%>/schedule/ticket.do";
	}
</script>

<h1>예매 완료</h1>
스케줄 번호: ${ schlist.schNo }
티켓 좌석번호: ${ tlist.ticseatno }
<br>
<input type="button" value="예매하기" onclick="goReservePage();">

