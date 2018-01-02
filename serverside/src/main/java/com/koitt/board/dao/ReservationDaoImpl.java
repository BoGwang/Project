package com.koitt.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Movie;
import com.koitt.board.model.Reservation;

@Repository
public class ReservationDaoImpl implements ReservationDao {

	private Logger logger = LogManager.getLogger(this.getClass());

	private static final String MAPPER_NAMESPACE = ReservationDaoImpl.class.getName();

	@Autowired
	private SqlSession sqlSession;

	public ReservationDaoImpl() {
	}

	// 영화 예매 하기
	@Override
	public void insert(Reservation reservation) throws CommonException {
		try {
			sqlSession.insert(MAPPER_NAMESPACE + ".insert", reservation);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E55: 영화 예매 실패");
		}
	}

	// 예매 내역 확인하기
	@Override
	public Reservation select(String rno) throws CommonException {
		Reservation reservation = null;

		try {
			reservation = sqlSession.selectOne(MAPPER_NAMESPACE + ".select", rno);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E08: 예매 검색 실패");
		}
		return reservation;

	}

	// 예매 전체 조회
	@Override
	public List<Reservation> selectAll() throws CommonException {
		List<Reservation> rlist = null;

		try {
			rlist = sqlSession.selectList(MAPPER_NAMESPACE + ".selectAll");

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E44:  영화 예매 조회 실패");
		}

		return rlist;
	}

	// 예매 갯수 가져오기
	@Override
	public int reservCount() throws CommonException {
		Integer rcount = 0;

		try {
			rcount = sqlSession.selectOne(MAPPER_NAMESPACE + ".count");

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E10: 예매 개수 가져오기 실패");
		}

		return rcount;
	}

	// 예매 취소
	@Override
	public void delete(String rno) throws CommonException {
		try {
			sqlSession.delete(MAPPER_NAMESPACE + ".delete", rno);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E88: 예매 취소 실패");
		}
	}

}
