package com.assignmentevaluationportal.serviceImpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.assignmentevaluationportal.config.JwtTokenUtil;
import com.assignmentevaluationportal.dto.response.JwtResponse;
import com.assignmentevaluationportal.service.JwtUserDetailsService;
import com.assignmentevaluationportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
	
	public UserServiceImpl(AuthenticationManager authenticationManager,
			JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public JwtResponse login(String username, String password, boolean isKeepMeSignedIn) throws Exception {
		authenticate(username, password);
        
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
        String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
        String refreshToken = null;
        if(isKeepMeSignedIn) {
        	refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        }
        return new JwtResponse(accessToken, refreshToken);
	}
	
	@Override
	public JwtResponse generateAccessToken(String refreshToken) throws Exception {
		String email = jwtTokenUtil.getUsernameFromToken(refreshToken);
		
		UserDetails userDetails = userDetailsService
                .loadUserByUsername(email);
		
		if(userDetails != null && !jwtTokenUtil.isTokenExpired(refreshToken)) {
			String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
			String newRefreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
			return new JwtResponse(accessToken, newRefreshToken);
		} else {
			throw new RuntimeException("Invalid refresh token: " + refreshToken);
		}
	}
	
	/**
	 * Authenticates the user
	 * @param username
	 * 				Email id of the user
	 * @param password
	 * 			Password of the user
	 * @return void
	 * @throws Exception
	 * 			In case of invalid credentials
	 * */
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
