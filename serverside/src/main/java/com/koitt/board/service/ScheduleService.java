package com.koitt.board.service;

import java.util.List;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Schedule;
import com.koitt.board.model.Screen;
import com.koitt.board.model.Theater;
import com.koitt.board.model.Ticket;

public interface ScheduleService {

	// 스케줄 번호를 1 증가하고 생성된다.
	public void newSchedule(Schedule bas) throws CommonException;

	// 스케쥴 하나 상세보기
	public Schedule detailSchedule(String schNo) throws CommonException;

	// 스케줄 전체 확인하기
	public List<Schedule> Schedulelist() throws CommonException;

	// 영화관 추가하기
	public int newTheater(Theater bas) throws CommonException;

	// 영화관 하나 가져오기
	public Theater detailTheater(String theNo) throws CommonException;

	// 영화관 전체 가져오기
	public List<Theater> Theaterlist() throws CommonException;

	// 영화관 수정하기
	public int modifyTheater(Theater bas) throws CommonException;
	
	//스크린
	public List<Screen> getScreenByNo(int theNo);
	
	// 예매 좌석
	public boolean isReservedSeats(Ticket bas);


	// 티켓 추가하기
	public void newTicket(Ticket bas) throws CommonException;

	// 티켓 하나 가져오기
	public Ticket detailTicket(String ticNo) throws CommonException;

	// 티켓 전체 가져오기
	public List<Ticket> Ticketlist() throws CommonException;

}
