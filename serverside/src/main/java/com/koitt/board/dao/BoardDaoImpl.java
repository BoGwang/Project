package com.koitt.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.Board;
import com.koitt.board.model.CommonException;

@Repository
public class BoardDaoImpl implements BoardDao {

	private Logger logger = LogManager.getLogger(this.getClass());

	private static final String MAPPER_NAMESPACE = BoardDaoImpl.class.getName();

	@Autowired
	private SqlSession sqlSession;

	public BoardDaoImpl() {
	}

	@Override
	public void insert(Board board) throws CommonException {
		try {
			sqlSession.insert(MAPPER_NAMESPACE + ".insert", board);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E04: 게시물 등록 실패");
		}
	}

	@Override
	public Board select(String no) throws CommonException {
		Board board = null;

		try {
			board = sqlSession.selectOne(MAPPER_NAMESPACE + ".select", no);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E01: 게시물 검색 실패");
		}

		return board;
	}

	@Override
	public List<Board> selectAll() throws CommonException {
		List<Board> list = null;

		try {
			list = sqlSession.selectList(MAPPER_NAMESPACE + ".selectAll");

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E02: 게시물 전체 검색 실패");
		}

		return list;
	}

	@Override
	public int boardCount() throws CommonException {
		Integer count = 0;

		try {
			count = sqlSession.selectOne(MAPPER_NAMESPACE + ".count");

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E09: 글 개수 가져오기 실패");
		}

		return count;
	}

	@Override
	public void update(Board board) throws CommonException {

		try {
			sqlSession.update(MAPPER_NAMESPACE + ".update", board);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E07: 게시물 수정 실패");
		}
	}

	@Override
	public void delete(String no) throws CommonException {
		try {
			sqlSession.delete(MAPPER_NAMESPACE + ".delete", no);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E08: 게시물 삭제 실패");
		}
	}

}
