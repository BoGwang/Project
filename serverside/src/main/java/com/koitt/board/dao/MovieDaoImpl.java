package com.koitt.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.Board;
import com.koitt.board.model.CommonException;
import com.koitt.board.model.Movie;

@Repository
public class MovieDaoImpl implements MovieDao {

	private Logger logger = LogManager.getLogger(this.getClass());

	private static final String MAPPER_NAMESPACE = MovieDaoImpl.class.getName();

	@Autowired
	private SqlSession sqlSession;

	public MovieDaoImpl() {
	}

	// 영화 등록 임플
	@Override
	public void insert(Movie movie) throws CommonException {
		try {
			sqlSession.insert(MAPPER_NAMESPACE + ".insert", movie);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E05: 영화 등록 실패");
		}
	}

	// 영화 수정
	@Override
	public void update(Movie movie) throws CommonException {

		try {
			sqlSession.update(MAPPER_NAMESPACE + ".update", movie);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E77: 영화 수정 실패");
		}
	}

	// 영화 한편 가져오는거 임플
	@Override
	public Movie select(String movieNo) throws CommonException {
		Movie movie = null;

		try {
			movie = sqlSession.selectOne(MAPPER_NAMESPACE + ".select", movieNo);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E08: 영화 검색 실패");
		}

		return movie;
	}

	@Override
	public List<Movie> selectAll() throws CommonException {
		List<Movie> mlist = null;

		try {
			mlist = sqlSession.selectList(MAPPER_NAMESPACE + ".selectAll");

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E44: 영화 전체 검색 실패");
		}

		return mlist;
	}
	
	@Override
	public void delete(String movieNo) throws CommonException {
		try {
			sqlSession.delete(MAPPER_NAMESPACE + ".delete", movieNo);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E08: 영화 삭제 실패");
		}
	}
	

}
