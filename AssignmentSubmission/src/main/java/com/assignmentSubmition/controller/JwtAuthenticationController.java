package com.assignmentSubmition.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignmentSubmition.config.JwtTokenUtil;
import com.assignmentSubmition.constants.ApiUrl;
import com.assignmentSubmition.dto.request.JwtRequest;
import com.assignmentSubmition.dto.response.JwtResponse;
import com.assignmentSubmition.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final Logger log = LoggerFactory.getLogger(JwtAuthenticationController.class);
    
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
    
    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
    		JwtUserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}
    
	@PostMapping(ApiUrl.AUTHENTICATION_ENDPOINT)
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