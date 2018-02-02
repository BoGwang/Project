package com.koitt.board.dao;

import java.util.List;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Coupon;
import com.koitt.board.model.UserInfo;

public interface UserInfoDao {

	public List<UserInfo> selectAll() throws CommonException;

	public UserInfo select(Integer id) throws CommonException;

	public void insert(UserInfo user) throws CommonException;

	public void insertUserTypes(UserInfo user) throws CommonException;

	public void delete(Integer id) throws CommonException;

	public void deleteUserTypes(Integer id) throws CommonException;

	public void update(UserInfo user) throws CommonException;

	public UserInfo selectByEmail(String email) throws CommonException;
	
	public int insertCoupon(Coupon coupon) throws CommonException;
	
	public List<Coupon> selectCoupondetail(Integer id) throws CommonException;
	
	
	
}
