package com.koitt.board.service;

import java.util.List;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Movie;

public interface MovieService {
	
	// 영화 번호를 1 증가하고 영화를 등록한다.
	public void newMovie(Movie movie) throws CommonException;
	
	// 영화 하나를 가져온다.
	public Movie detail(String mno) throws CommonException;
	
	// 영화 전체를 가져온다.
	public List<Movie> list() throws CommonException;
	
	// 영화 개수를 가져온다.
	public int count() throws CommonException;
	
	// 영화를 수정한다. (수정되기 전 영화명을 리턴)
	public String modify(Movie movie) throws CommonException;
	
	// 영화를 삭제한다. (삭제하는 영화를 리턴)
	public String remove(String mno) throws CommonException;
	

}
