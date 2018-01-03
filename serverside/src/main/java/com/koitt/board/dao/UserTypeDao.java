package com.koitt.board.dao;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.UserType;

public interface UserTypeDao {
	
	public UserType select(Integer id) throws CommonException;

}
