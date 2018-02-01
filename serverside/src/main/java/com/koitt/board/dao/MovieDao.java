package com.koitt.board.dao;

import java.util.List;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Movie;

public interface MovieDao {

	// 영화 등록
	public void insert(Movie movie) throws CommonException;

	// 영화 수정하기
	public void update(Movie movie) throws CommonException;

	// movNo로 영화조회
	public Movie select(String movieNo) throws CommonException;

	// 전체 영화 가져오기
	public List<Movie> selectAll() throws CommonException;
	
	// 영화 삭제하기
	public void delete(String movieNo) throws CommonException;

}
