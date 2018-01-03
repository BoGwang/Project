package com.koitt.board.dao;

import java.util.List;

import com.koitt.board.model.Board;
import com.koitt.board.model.CommonException;

public interface BoardDao {
	
	// 게시판 글 등록
	public void insert(Board board) throws CommonException;
	
	// 게시판 글 하나 가져오기
	public Board select(String no) throws CommonException;
	
	// 전체 글 가져오기
	public List<Board> selectAll() throws CommonException;
	
	// 전체 글 개수 가져오기
	public int boardCount() throws CommonException;
	
	// 글 수정하기
	public void update(Board board) throws CommonException;
	
	// 글 삭제하기
	public void delete(String no) throws CommonException;
}
