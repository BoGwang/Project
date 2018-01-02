package com.koitt.board.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koitt.board.model.UserInfo;
import com.koitt.board.model.UserType;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private UserInfoService service;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserInfo userInfo = service.detail(email);
		logger.debug("UserInfo: " + userInfo);
		
		if (userInfo == null) {
			throw new UsernameNotFoundException("E20: 사용자 정보를 찾지 못했습니다.");
		}
		
		// username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities
		return new User(userInfo.getEmail(), 
				userInfo.getPassword(),
				true, 
				true, 
				true, 
				true, 
				this.getGrantedAuthorities(userInfo));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(UserInfo userInfo) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for (UserType item : userInfo.getUserTypes()) {
			logger.debug("UserType: " + item);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + item.getType()));
		}
		
		logger.debug("authorities: " + authorities);
		return authorities;
	}

}
