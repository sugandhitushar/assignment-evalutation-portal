package com.assignmentevaluationportal.service;

import com.assignmentevaluationportal.dto.response.JwtResponse;
import com.assignmentevaluationportal.model.User;

public interface UserService {
	
	/**
	 * Service for user Login
	 * @param username username
	 * @param password password
	 * @param keepMeSignedIn  Keep me signed in flag issues a refresh token for the user  to enable silent re-authentication.
	 * @return JwtResponse JWT tokens for the user
	 * */
	public JwtResponse login(String username, String password, boolean keepMeSignedIn) throws Exception;
	
	/**
	 * Generate new access token from a refresh token
	 * @param refreshToken Refresh Token
	 * @return JwtResponse JWT token for the user
	 * */
	public JwtResponse generateAccessToken(String refreshToken) throws Exception;
	
	/**
	 * Verify User
	 * @param id User id
	 * @return User Verified user
	 * */
	public User verifyUser(Long id);
	
	/**
	 * Reject User
	 * @param id User id
	 * @return void
	 * */
	public void rejectUser(Long id);

}
