package com.koitt.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.board.dao.BoardDao;
import com.koitt.board.model.Board;
import com.koitt.board.model.CommonException;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;

	public BoardServiceImpl() {}

	@Override
	public void newBoard(Board board) throws CommonException {
		// DB에 저장
		dao.insert(board);
	}

	@Override
	public Board detail(String no) throws CommonException {
		return dao.select(no);
	}

	@Override
	public List<Board> list() throws CommonException {
		return dao.selectAll();
	}

	@Override
	public int count() throws CommonException {
		return dao.boardCount();
	}

	@Transactional
	@Override
	public String modify(Board board) throws CommonException {
		/*
		 *  파라메터의 board 객체는 이미 수정된 정보를 담고 있다.
		 *  따라서 기존 데이터베이스에서 글번호로 기존 데이터를 불러온다.
		 */
		Board item = dao.select(Integer.toString(board.getNo()));
		String oldFilename = item.getAttachment();
		dao.update(board);

		return oldFilename;
	}

	@Transactional
	@Override
	public String remove(String no) throws CommonException {
		Board item = dao.select(no);
		String filename = item.getAttachment();
		dao.delete(no);

		return filename;
	}
}
