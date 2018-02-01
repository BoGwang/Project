package com.koitt.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.board.dao.MovieDao;
import com.koitt.board.model.Board;
import com.koitt.board.model.CommonException;
import com.koitt.board.model.Movie;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao dao;

	public MovieServiceImpl() {}

	@Override
	public void newMovie(Movie movie) throws CommonException {
		// DB에 저장
		dao.insert(movie);
	}

	@Override
	public Movie detail(String movieNo) throws CommonException {
		return dao.select(movieNo);
	}

	@Override
	public List<Movie> listAll() throws CommonException {
		return dao.selectAll();
	}


	@Transactional
	@Override
	public String modifyMovie(Movie movie) throws CommonException {
		/*
		 *  파라메터의 movie 객체는 이미 수정된 정보를 담고 있다.
		 *  따라서 기존 데이터베이스에서 글번호로 기존 데이터를 불러온다.
		 */
		Movie item = dao.select(Integer.toString(movie.getMovieNo()));
		String newFilename = item.getMovieposter();
		dao.update(movie);
    
		return newFilename;
	}
	
	@Transactional
	@Override
	public String remove(String movieNo) throws CommonException {
		Movie item = dao.select(movieNo);
		String filename = item.getMovieposter();
		dao.delete(movieNo);

		return filename;
	}


}
