package com.koitt.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Schedule;
import com.koitt.board.model.Screen;
import com.koitt.board.model.Theater;
import com.koitt.board.model.Ticket;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {

	private Logger logger = LogManager.getLogger(this.getClass());

	private static final String MAPPER_NAMESPACE = ScheduleDaoImpl.class.getName();

	@Autowired
	private SqlSession sqlSession;

	public ScheduleDaoImpl() {
	}

	// 등록
	@Override
	public int insertTheater(Theater bas) throws CommonException {

		return sqlSession.insert(MAPPER_NAMESPACE + ".insertTheater", bas);

	}

	// 조회
	@Override
	public Theater selectTheater(String theNo) throws CommonException {
		Theater theater = null;

		try {
			theater = sqlSession.selectOne(MAPPER_NAMESPACE + ".selectTheater", theNo);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E01: 영화관 검색 실패");
		}

		return theater;
	}

	@Override
	public List<Theater> selectAllTheater() throws CommonException {
		List<Theater> list = null;

		try {
			list = sqlSession.selectList(MAPPER_NAMESPACE + ".selectAllTheater");

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E02: 영화관 전체 검색 실패");
		}

		return list;
	}

	@Override
	public int updateTheater(Theater bas) throws CommonException {

		return sqlSession.update(MAPPER_NAMESPACE + ".updateTheater", bas);

	}

	// 등록
	@Override
	public void insertSchedule(Schedule bas) throws CommonException {
		try {
			sqlSession.insert(MAPPER_NAMESPACE + ".insertSchedule", bas);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E04: 스케줄 등록 실패");
		}
	}

	// 조회
	@Override
	public Schedule selectSchedule(String schNo) throws CommonException {
		Schedule schedule = null;

		try {
			schedule = sqlSession.selectOne(MAPPER_NAMESPACE + ".selectSchedule", schNo);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E01: 스케줄 검색 실패");
		}

		return schedule;
	}

	@Override
	public List<Schedule> selectAllSchedule() throws CommonException {
		List<Schedule> list = null;

		try {
			list = sqlSession.selectList(MAPPER_NAMESPACE + ".selectAllSchedule");

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E02: 스케줄 전체 검색 실패");
		}

		return list;
	}

	@Override
	public void updateSchedule(Schedule bas) throws CommonException {

		try {
			sqlSession.update(MAPPER_NAMESPACE + ".updateSchedule", bas);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E07: 스케줄 수정 실패");
		}
	}
	
	@Override
	public boolean isReservedSeats(Ticket bas) {
		int count = sqlSession.selectOne(MAPPER_NAMESPACE + "isReservedSeats", bas);
		return count != 0;
	}

	// 등록
	@Override
	public void insertTicket(Ticket bas) throws CommonException {
		try {
			sqlSession.insert(MAPPER_NAMESPACE + ".insertTicket", bas);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E04: 티켓 등록 실패");
		}
	}

	// 조회
	@Override
	public Ticket selectTicket(String ticNo) throws CommonException {
		Ticket ticket = null;

		try {
			ticket = sqlSession.selectOne(MAPPER_NAMESPACE + ".selectTicket", ticNo);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E01: 티켓 검색 실패");
		}

		return ticket;
	}

	@Override
	public List<Ticket> selectAllTicket() throws CommonException {
		List<Ticket> list = null;

		try {
			list = sqlSession.selectList(MAPPER_NAMESPACE + ".selectAllTicket");

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E02: 티켓 전체 검색 실패");
		}

		return list;
	}

	@Override
	public List<Screen> selectAllScreen() throws CommonException {
		List<Screen> list = null;

		try {
			list = sqlSession.selectList(MAPPER_NAMESPACE + ".selectAllScreen");

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E02: 상영관 전체 검색 실패");
		}

		return list;
	}

	@Override
	public int insertScreen(int theNo, int count) {
		int cnt = 0;
		Screen bas = new Screen();
		bas.setTheNo(theNo);
		bas.setScLine(8);
		bas.setScSeat(8);
		for (int i = 1; i <= count; i++) {
			bas.setScName(i + "상영관");
			cnt += sqlSession.insert(MAPPER_NAMESPACE + ".insertScreen", bas);
		}
		return cnt;
	}

	// 조회
	@Override
	public Screen selectScreen(String scNo) throws CommonException {
		Screen screen = null;

		try {
			screen = sqlSession.selectOne(MAPPER_NAMESPACE + ".selectScreen", scNo);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E01: 상영관 검색 실패");
		}

		return screen;
	}
	
	@Override
	public List<Screen> selectScreenListByNo(int theNo) {
		return sqlSession.selectList(MAPPER_NAMESPACE + "selectScreenListByNo", theNo);
	}

}
