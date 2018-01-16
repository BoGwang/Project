package com.koitt.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.board.dao.CouponDao;
import com.koitt.board.model.CommonException;
import com.koitt.board.model.Coupon;
import com.koitt.board.model.Movie;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponDao dao;

	public CouponServiceImpl() {}

	@Override
	public void newCoupon(Coupon coupon) throws CommonException {
		// DB에 저장
		dao.insert(coupon);
	}

	@Override
	public Coupon detail(String cno) throws CommonException {
		return dao.select(cno);
	}

	@Override
	public List<Coupon> list() throws CommonException {
		return dao.selectAll();
	}

	@Override
	public int count() throws CommonException {
		return dao.couponCount();
	}

}
