package com.koitt.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.UserType;

@Repository
public class UserTypeDaoImpl implements UserTypeDao {

	private Logger logger = LogManager.getLogger(this.getClass());

	private static final String MAPPER_NAMESPACE = UserTypeDaoImpl.class.getName();

	@Autowired
	private SqlSession sqlSession;
	
	public UserTypeDaoImpl() {}

	@Override
	public UserType select(Integer id) throws CommonException {
		UserType userType = null;

		try {
			userType = sqlSession.selectOne(MAPPER_NAMESPACE + ".select", id);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E40: 사용자 타입 검색 실패");
		}

		return userType;
	}

}
