package com.koitt.board.dao;

import java.util.List;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Coupon;
import com.koitt.board.model.Movie;

public interface CouponDao {
	
	// 쿠폰 받기
	public void  insert(Coupon coupon) throws CommonException;
	
	// 쿠폰 하나 가져오기
    public Coupon select(String cno) throws CommonException;
    
    // 전체 쿠폰 가져오기
    public List<Coupon> selectAll() throws CommonException;
    
    // 전체 쿠폰 개수 가져오기
    public int couponCount() throws CommonException;
    
	
}
