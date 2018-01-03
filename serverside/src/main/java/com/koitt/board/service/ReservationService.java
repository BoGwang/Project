package com.koitt.board.service;

import java.util.List;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Reservation;

public interface ReservationService {

	// 예매 번호를 1 증가하고 예매가 된다.
	public void newReservation(Reservation reservation) throws CommonException;

	// 예매된것을 하나 가져온다.
	public Reservation detail(String rno) throws CommonException;

	// 예매 내역 전체 확인하기
	public List<Reservation> list() throws CommonException;

	// 전체 예매 개수 가져오기
	public int reservCount() throws CommonException;

	// 예매를 취소한다.
	public String remove(String rno) throws CommonException;

}
