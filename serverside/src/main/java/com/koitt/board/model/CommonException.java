package com.koitt.board.model;

public class CommonException extends RuntimeException {
	
	private Throwable throwable;
	
	public CommonException(String message) {
		super(message);
	}
	
	public CommonException(String message, Throwable throwable) {
		super(message);
		this.throwable = throwable;
	}
	
	@Override
	public synchronized Throwable getCause() {
		return throwable;
	}

}
