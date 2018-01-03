package com.koitt.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koitt.board.dao.UserTypeDao;
import com.koitt.board.model.UserType;

@Service
public class UserTypeServiceImpl implements UserTypeService {
	
	@Autowired
	private UserTypeDao dao;

	@Override
	public UserType findById(Integer id) {
		return dao.select(id);
	}
}
