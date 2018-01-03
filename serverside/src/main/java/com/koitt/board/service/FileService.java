package com.koitt.board.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.koitt.board.model.CommonException;

public interface FileService {
	
	// 파일 삭제
	public void remove(HttpServletRequest request, String repo, String filename) throws CommonException, UnsupportedEncodingException;
}
