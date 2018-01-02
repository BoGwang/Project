package com.koitt.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.koitt.board.model.UserType;
import com.koitt.board.service.UserTypeService;

@Component
public class RoleToUserTypeConverter implements Converter<Object, UserType> {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	UserTypeService service;

	// UserType을 id 값으로 가져올 때
	@Override
	public UserType convert(Object source) {
		Integer id = Integer.parseInt((String) source);
		UserType userType = service.findById(id);
		logger.debug("UserType: " + userType);
		
		return userType;
	}

}
