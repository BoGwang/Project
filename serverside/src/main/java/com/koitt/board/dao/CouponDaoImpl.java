package com.koitt.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Coupon;
import com.koitt.board.model.Movie;

@Repository
public class CouponDaoImpl implements CouponDao {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private static final String MAPPER_NAMESPACE = CouponDaoImpl.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	public CouponDaoImpl() {}

	// 영화 등록 임플
	@Override
	public void insert(Coupon coupon) throws CommonException {
		try {
			sqlSession.insert(MAPPER_NAMESPACE + ".insert", coupon);
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E05: 쿠폰 받기 실패");
		}
	}

	// 영화 한편 가져오는거 임플
	@Override
	public Coupon select(String cno) throws CommonException {
		Coupon coupon = null;
		
		try {
			coupon = sqlSession.selectOne(MAPPER_NAMESPACE + ".select", cno);
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E08: 영화 검색 실패");
		}
		
		return coupon;
	}

	@Override
	public List<Coupon> selectAll() throws CommonException {
		List<Coupon> clist = null;
		
		try {
			clist = sqlSession.selectList(MAPPER_NAMESPACE + ".selectAll");
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E44: 쿠폰 전체 검색 실패");
		}
		
		return clist;
	}

	@Override
	public int couponCount() throws CommonException {
		Integer ccount = 0;
		
		try {
			ccount = sqlSession.selectOne(MAPPER_NAMESPACE + ".count");
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E10: 전체 쿠폰 개수 가져오기 실패");
		}
		
		return ccount;
	}



}
