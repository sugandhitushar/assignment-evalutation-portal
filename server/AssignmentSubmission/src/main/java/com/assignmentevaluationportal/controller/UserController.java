package com.assignmentevaluationportal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignmentevaluationportal.config.JwtTokenUtil;
import com.assignmentevaluationportal.constants.ApiUrl;
import com.assignmentevaluationportal.dto.request.JwtRequest;
import com.assignmentevaluationportal.dto.response.JwtResponse;
import com.assignmentevaluationportal.service.JwtUserDetailsService;

@RestController
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
    
    public UserController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
    		JwtUserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}
    
    /**
     * Endpoint for login
     * @param request-body 
     * 			A request of type {@link JwtRequest}
     * @return JWT Token
     */
	@PostMapping(ApiUrl.LOGIN_ENDPOINT)
    public ResponseEntity<?> generateJwtAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		log.info("Authentication request");
		
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        
        log.info("Username: " + authenticationRequest.getUsername());
        log.info("Password: " + authenticationRequest.getPassword());
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
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