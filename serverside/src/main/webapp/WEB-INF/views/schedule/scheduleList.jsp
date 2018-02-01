<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<form id="frm"
	action="<%=request.getContentType()%>/schedule/setSchedule.do"
	method="post" onsubmit="return checkVal();">
	<input type="hidden" name="mno" id="mno" value="${ mno }"> <input
		type="hidden" name="srday" id="srday"> <input type="hidden"
		name="mscr" id="mscr" value="${ mscr }"> <input type="hidden"
		name="srst" id="srst" value="${srst }"> <input type="hidden"
		name="sret" id="sret" value="${ sret }">
	<table>
		<thead>
			<tr>
				<td>영화></td>
				<td>상영일</td>
				<td>스케줄 추가</td>
			</tr>
		</thead>
		<tbody>
			<tr>

				<td><select id="movieSel">
						<options value="default">영화 제목</options>
						<c:forEach items="$ {mlist}" var="movie">
							<c:choose>
								<c:when test="false">
									<options value="${movie.mno }" selected="selected">${movie.mtitle }</options>
								</c:when>
								<c:otherwise>
									<options value="${movie.mno }">${movie.mtitle }</options>

								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>
				<td><input type="text" id="date" name="date"
					onClick="datePicker(even,'date')" readonly="readonly"></td>
				<td><input type="button" value="추가" id="addSchedule"></td>
			</tr>
	</table>
	<table>
		<thead>
			<tr>
				<td>상영관 이름</td>
				<td>영화</td>
				<td>상영 시간</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${slist }" var="schedule">
				<tr>
					<td>${ schedule.mscr }</td>
					<td>${ schedule.mtitle }</td>
					<td>${ schedule.srday }</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>



</form>