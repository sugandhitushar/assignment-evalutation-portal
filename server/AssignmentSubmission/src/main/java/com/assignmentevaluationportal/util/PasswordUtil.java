package com.assignmentevaluationportal.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public static String encrypt(String password)  {
		return encoder.encode(password);
	}

}
