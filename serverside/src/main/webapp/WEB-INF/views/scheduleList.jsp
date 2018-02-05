<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<form id="frm"
	action="<%=request.getContentType()%>/schedule/setSchedule.do"
	method="post" onsubmit="return checkVal();">
	<input type="hidden" name="scNo" id="scNo" value="${ scNo }">
	 <input type="hidden" name="theNo" id="theNo" value="${ theNo }"> 
	 <input type="hidden"	name="movNo" id="movNo" value="${ movNo }"> 
		<input type="hidden"name="schstarttime" id="schstarttime" value="${schstarttime }"> 
		
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
						<option value="default">영화 제목</option>
						<c:forEach items="$ {mlist}" var="movie">
							<c:choose>
								<c:when test="false">
									<option value="${movie.movieNo }" selected="selected">${movie.movietitle }</option>
								</c:when>
								<c:otherwise>
									<option value=" ${movie.movieNo }">${movie.movietitle }
									</option>

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
			<c:forEach items="${schlist }" var="schedule">
				<tr>
					<td>${ schedule.movNo }</td>
					<td>${ schedule.scname }</td>
					<td>${ schedule.scNo }</td>
					<td>${ schedule.schstarttime }</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>



</form>