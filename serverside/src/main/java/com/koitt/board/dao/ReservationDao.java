package com.koitt.board.dao;

import java.sql.Date;
import java.util.List;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Movie;
import com.koitt.board.model.Reservation;

public interface ReservationDao {
	
	// 영화 예매 하기
	public void insert(Reservation reservation) throws CommonException;
	
	// 예매 내역 확인하기
	public Reservation select(String rno) throws CommonException;
	
	// 예매 내역 전체 확인하기
	public List<Reservation> selectAll() throws CommonException;
	
	// 영화 예매 취소하기
	public void delete(String rno) throws CommonException;
	
	 // 전체 예매 개수 가져오기
    public int reservCount() throws CommonException;
    

}
