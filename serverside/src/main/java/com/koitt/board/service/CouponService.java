package com.koitt.board.service;

import java.util.List;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Coupon;
import com.koitt.board.model.Movie;

public interface CouponService {
	
	
	// 쿠폰 하나를 가져온다.
	public Coupon detail(String cno) throws CommonException;
	
	// 영화 전체를 가져온다.
	public List<Coupon> list() throws CommonException;
	
	


}
