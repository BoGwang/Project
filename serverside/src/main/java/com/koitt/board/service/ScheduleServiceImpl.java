package com.koitt.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.board.dao.ScheduleDao;
import com.koitt.board.model.CommonException;
import com.koitt.board.model.Schedule;
import com.koitt.board.model.Screen;
import com.koitt.board.model.Theater;
import com.koitt.board.model.Ticket;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDao dao;

	public ScheduleServiceImpl() {
	}

	@Override
	public void newSchedule(Schedule bas) throws CommonException {
		// DB에 저장
		dao.insertSchedule(bas);
	}

	// 스케줄 하나 가져오기
	@Override
	public Schedule detailSchedule(String schNo) throws CommonException {
		return dao.selectSchedule(schNo);
	}

	// 스케줄 전체 가죠오기
	@Override
	public List<Schedule> Schedulelist() throws CommonException {
		return dao.selectAllSchedule();
	}

	/**
	 * 극장을 생성하는 메소드
	 */
	@Override
	public int newTheater(Theater bas) {
		int cnt = dao.insertTheater(bas);
		/* 임시적으로 극장 생성시 3개의 상영관을 생성한다. */
		dao.insertScreen(bas.getTheNo(), 3);
		return cnt;
	}

	// 스케줄 하나 가져오기
	@Override
	public Theater detailTheater(String theNo) throws CommonException {
		return dao.selectTheater(theNo);

	}

	// 스케줄 전체 가죠오기
	@Override
	public List<Theater> Theaterlist() throws CommonException {
		return dao.selectAllTheater();
	}

	@Transactional
	@Override
	public int modifyTheater(Theater bas) throws CommonException {

		return dao.updateTheater(bas);

	}

	@Override
	public void newTicket(Ticket bas) throws CommonException {
		// DB에 저장
		dao.insertTicket(bas);
	}
	
	@Override
	public boolean isReservedSeats(Ticket bas) {
		return dao.isReservedSeats(bas);
	}

	// 스케줄 하나 가져오기
	@Override
	public Ticket detailTicket(String ticNo) throws CommonException {
		return dao.selectTicket(ticNo);
	}
	
	@Override
	public int getScheduleCount(Screen bas) {
		return dao.selectScheduleCount(bas);
	}
	

	// 스케줄 전체 가죠오기
	@Override
	public List<Ticket> Ticketlist() throws CommonException {
		return dao.selectAllTicket();
	}
	
	@Override
	public List<Screen> getScreenByNo(int theNo) {
		return dao.selectScreenListByNo(theNo);
	}

}
