package com.assignmentevaluationportal.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignmentevaluationportal.constants.ApiUrl;
import com.assignmentevaluationportal.dto.request.JwtRequest;
import com.assignmentevaluationportal.dto.response.JwtResponse;
import com.assignmentevaluationportal.service.UserService;

@RestController
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    
    private UserService userService;
    
    public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
     * Endpoint for login
     * @param request-body 
     * 			A request of type {@link JwtRequest}
     * @return JWT Token
     */
	@PostMapping(ApiUrl.LOGIN_ENDPOINT)
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
		log.info("Login API:: username: {}", authenticationRequest.getUsername());
        log.debug("Password: " + authenticationRequest.getPassword());
		
		JwtResponse response = userService.login(authenticationRequest.getUsername(), 
				authenticationRequest.getPassword(), authenticationRequest.isKeepMeSignedIn());
		
		return ResponseEntity.ok(response);
    }
	
	/**
     * Endpoint for refreshing the access token
     * @param request-body 
     * 			A request of type {@link JwtRequest}
     * @return JWT Token
     */
	@PostMapping(ApiUrl.REFRESH_TOKEN_ENDPOINT)
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> requestBody) throws Exception {
		log.info("Refresh Token API:: refreshToken: {}", requestBody.get("refreshToken"));
		
		JwtResponse response = userService.generateAccessToken(requestBody.get("refreshToken"));
		
		return ResponseEntity.ok(response);
    }
	
	
	
}