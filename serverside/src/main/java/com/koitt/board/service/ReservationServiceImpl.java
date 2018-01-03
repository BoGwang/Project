package com.koitt.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.board.dao.ReservationDao;
import com.koitt.board.model.CommonException;
import com.koitt.board.model.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao dao;

	public ReservationServiceImpl() {
	}

	@Override
	public void newReservation(Reservation reservation) throws CommonException {
		// DB에 저장
		dao.insert(reservation);
	}

	@Override
	public Reservation detail(String rno) throws CommonException {
		return dao.select(rno);
	}

	@Override
	public List<Reservation> list() throws CommonException {
		return dao.selectAll();
	}

	@Override
	public int reservCount() throws CommonException {
		return dao.reservCount();
	}

	@Transactional
	@Override
	public String remove(String rno) throws CommonException {
		Reservation item = dao.select(rno);
		dao.delete(rno);

		return null;
	}

}
