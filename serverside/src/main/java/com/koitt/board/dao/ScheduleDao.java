package com.koitt.board.dao;

import java.util.List;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Schedule;
import com.koitt.board.model.Screen;
import com.koitt.board.model.Theater;
import com.koitt.board.model.Ticket;

public interface ScheduleDao {

	// 영화관 등록
	public abstract int insertTheater(Theater bas) throws CommonException;

	// 영화관 하나 가져오기
	public Theater selectTheater(String theNo) throws CommonException;

	// 전체 영화관 하나 가져오기
	public List<Theater> selectAllTheater() throws CommonException;

	// 영화관 수정하기
	public abstract int updateTheater(Theater bas) throws CommonException;

	// 스케줄 추가
	public void insertSchedule(Schedule bas) throws CommonException;

	// 스케줄 하나 가져오기
	public Schedule selectSchedule(String schNo) throws CommonException;

	// 스케줄 전체 가져오기
	public List<Schedule> selectAllSchedule() throws CommonException;

	// 스케줄 수정하기
	public void updateSchedule(Schedule bas) throws CommonException;

	// 티켓 추가하기
	public void insertTicket(Ticket bas) throws CommonException;

	// 티켓 하나 가져오기
	public Ticket selectTicket(String ticNo) throws CommonException;

	// 티켓 전체 가져오기
	public List<Ticket> selectAllTicket() throws CommonException;
	
	public abstract boolean isReservedSeats(Ticket bas);
	
	// 상영관  전체 가져오기
	public List<Screen> selectAllScreen() throws CommonException;
	
	// 상영관 추가하기
	public int insertScreen(int theNo, int cnt) throws CommonException;
	
	// 상영관 하나 가져오기
	public Screen selectScreen(String scNo) throws CommonException;
	
	//스케줄 하나 가져오기
	public abstract int selectScheduleCount(Screen bas) throws CommonException;

	//스크린
	public abstract List<Screen> selectScreenListByNo(int theNo);

}
