<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/script/jquery.js"></script>
<script type="text/javascript">
function goReservePage() {
	window.location = "<%=request.getContextPath() %>/theater/movieSchedule.do";
}
</script>

<h1>예매 완료</h1>
상영 시간 : 