package com.koitt.board.model;

public enum UserTypeId {
	ADMIN(1),
	USER(2);

	Integer id;
	
	private UserTypeId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserTypeId() {
		return this.id;
	}
	
}
