<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

$(document).ready(function() {
    $("#addSchedule").on("click", function() {
       $("schDate").val($("#date").val());
      var scheduleCount = 0;
      if($("#mouseSel").val()=="default"){
                    alert("영화를 선택하세요.");
            return false;
    } else if	 (!$("#date").val()) {
        alert("상영일을 입력하세요.");
        return false;
    }
    $.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/schedule/getScheduleCount.do",
			data: {
    		    scNo:"${scNo}",
    		    movieNo:$("#movieSel").val(),
    		    schstarttime: $("#schDate").val() 
    		    	},
    		   dataType: "JSON",
    		   success: function(ret) {
           if(ret == "0"){
             $.ajax({
            type:"POST",
            url:"<%=request.getContextPath()%>/schedule/newschedule.do",
            data:{
					scNo: "${ scNo}",
					movieNo: $("#movieSel").val(),
					schstarttime:$("schDate").val(),
					theNo:$("#theNo").val()
            },
            dataType: "JSON",
            success: function(ret) {
	 		if(ret == "1"){
  				alert("상영 스케줄을 등록햇습니다.");
  				 location.reload();

	 		}else{
		 			alert("상영 스케줄을 등로에 실패하였습니다.");
	 		}
            },
            error:function(){
    			alert("상영 스케줄 등록에 실패하였습니다.");
            }
             });
           } else {
          alert("상영 스케줄이 존재합니다.")
               }
              },
              error:function(){
           			alert("상영 스케줄 등록에 실패하였습니다.")
                  }
    });
    });
});




</script>



<!DOCTYPE html>
<form id="frm"
	action="<%=request.getContentType()%>/schedule/newSchedule.do"
	method="post" onsubmit="return checkVal();">
	<input type="hidden" name="scNo" id="scNo" value="${ scNo }"> <input
		type="hidden" name="theNo" id="theNo" value="${ theNo }"> <input
		type="hidden" name="movieNo" id="movieNo" value="${ movieNo }">
	<input type="hidden" name="schstarttime" id="schstarttime"
		value="${schstarttime }">

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
						<c:forEach items="${mlist}" var="mlist">
							<c:choose>
								<c:when test="false">
									<option value="${mlist.movieNo }" selected="selected">${mlist.movietitle }</option>
								</c:when>
								<c:otherwise>
									<option value=" ${mlist.movieNo }">${mlist.movietitle }
									</option>

								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>
				<td><input type="text" id="date" name="date"
					onClick="datePicker(even,'date')" readonly="readonly"></td>
				<td><input type="button" value="추가" id="addSchedule"></td>
			</tr>
		</tbody>
	</table>
	<table border="1">
		<thead>
			<tr>
				<td>영화</td>
				<td>상영관 이름</td>
				<td>상영 시간</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ schlist }" var="schlist">
				<tr>
					<td>${ schlist.movieNo }</td>
					<td>${ schlist.scname }</td>
					<td>${ schlist.scNo }</td>
					<td>${ schlist.schstarttime }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</form>