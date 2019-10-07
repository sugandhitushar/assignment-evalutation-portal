package com.assignmentevaluationportal.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.assignmentevaluationportal.config.JwtTokenUtil;
import com.assignmentevaluationportal.constants.AEPError;
import com.assignmentevaluationportal.dto.response.JwtResponse;
import com.assignmentevaluationportal.exception.AEPException;
import com.assignmentevaluationportal.model.User;
import com.assignmentevaluationportal.repository.UserRepository;
import com.assignmentevaluationportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;

	public UserServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
			UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userRepository = userRepository;
	}

	@Override
	public JwtResponse login(String username, String password, boolean isKeepMeSignedIn) throws Exception {
		authenticate(username, password);
        
        User user = userRepository.findByEmail(username);
        
        if(user == null) {
        	throw new AEPException(HttpStatus.BAD_REQUEST, AEPError.USER_NOT_FOUND);
        }
        
        String accessToken = jwtTokenUtil.generateAccessToken(user);
        String refreshToken = null;
        if(isKeepMeSignedIn) {
        	refreshToken = jwtTokenUtil.generateRefreshToken(user);
        }
        return new JwtResponse(accessToken, refreshToken);
	}
	
	@Override
	public JwtResponse generateAccessToken(String refreshToken) {
		
		if(jwtTokenUtil.isTokenExpired(refreshToken)) {
			throw new AEPException(HttpStatus.BAD_REQUEST, AEPError.REFRESH_TOKEN_EXPIRED);
		}
		
		String email = jwtTokenUtil.getUsernameFromToken(refreshToken);
		
		User user = userRepository.findByEmail(email);
		
		if(user != null) {
			String accessToken = jwtTokenUtil.generateAccessToken(user);
			String newRefreshToken = jwtTokenUtil.generateRefreshToken(user);
			return new JwtResponse(accessToken, newRefreshToken);
		} else {
			throw new AEPException(HttpStatus.BAD_REQUEST, AEPError.USER_NOT_FOUND);
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
