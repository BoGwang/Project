package com.koitt.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.UserInfo;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {

	private Logger logger = LogManager.getLogger(this.getClass());

	private static final String MAPPER_NAMESPACE = UserInfoDaoImpl.class.getName();

	@Autowired
	private SqlSession sqlSession;

	public UserInfoDaoImpl() {}

	@Override
	public List<UserInfo> selectAll() throws CommonException {
		List<UserInfo> list = null;			

		try {
			list = sqlSession.selectList(MAPPER_NAMESPACE + ".selectAll");

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E30: 사용자 전체 검색 실패");
		}

		return list;
	}

	@Override
	public UserInfo select(Integer id) throws CommonException {
		UserInfo userInfo = null;

		try {
			userInfo = sqlSession.selectOne(MAPPER_NAMESPACE + ".select", id);

		} catch (Exception e) {
			e.printStackTrace();
			//logger.debug(e.getMessage());
			throw new CommonException("E31: 사용자 검색 실패");
		}

		return userInfo;
	}

	@Override
	public void insert(UserInfo user) {
		try {
			sqlSession.insert(MAPPER_NAMESPACE + ".insert", user); 

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			throw new CommonException("E32: 사용자 등록 실패");
		}
	}

	@Override
	public void insertUserTypes(UserInfo user) throws CommonException {
		try {
			sqlSession.insert(MAPPER_NAMESPACE + ".insertUserTypes", user);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E33: 사용자 타입 등록 실패");
		}
	}

	@Override
	public void delete(Integer id) throws CommonException{
		try {
			sqlSession.delete(MAPPER_NAMESPACE+ ".delete", id);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E35: 사용자 삭제 실패");
		}

	}

	@Override
	public void deleteUserTypes(Integer id) throws CommonException{
		try {
			sqlSession.delete(MAPPER_NAMESPACE+ ".deleteUserTypes", id);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E36: 사용자 타입 삭제 실패");
		}
	}

	@Override
	public void update(UserInfo user) {
		try {
			sqlSession.update(MAPPER_NAMESPACE + ".update", user);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("E37: 사용자 수정 실패");
		}
	}

	@Override
	public UserInfo selectByEmail(String email) throws CommonException {
		UserInfo userInfo = null;

		try {
			userInfo = sqlSession.selectOne(MAPPER_NAMESPACE + ".selectByEmail", email);

		} catch (Exception e) {
			e.printStackTrace();
			//logger.debug(e.getMessage());
			throw new CommonException("E38: 사용자 검색 실패");
		}

		return userInfo;
	}
}
