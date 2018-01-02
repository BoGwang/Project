package com.koitt.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestDrive {
	
	public static void main(String[] args) {
		String password = "s3cret";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoding = encoder.encode(password);
		System.out.println(encoding);
	}
}
