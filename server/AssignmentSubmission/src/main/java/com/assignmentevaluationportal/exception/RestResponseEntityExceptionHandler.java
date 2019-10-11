package com.assignmentevaluationportal.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignmentevaluationportal.constants.AEPError;
import com.assignmentevaluationportal.dto.response.Response;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = {AEPException.class})
	public ResponseEntity<Object> handleAEPException(AEPException ex, WebRequest request) {
		logger.error("AEPException error: " + ex.getErrors());
		return ResponseEntity.status(ex.status).body(Response.getErrorResponse(ex.errors));
	}
	
	@ExceptionHandler(value = {ExpiredJwtException.class})
	public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
		logger.error("ExpiredJwtException: {}", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.getErrorResponse(Arrays.asList(AEPError.REFRESH_TOKEN_EXPIRED)));
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("MethodArgumentNotValidException: {}", ex);
		List<AEPError> errors = new ArrayList<>();
		
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.add(AEPError.getAEPError(error.getDefaultMessage()));
	    }
		
		logger.error("MethodArgumentNotValidException: errors: {}", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.getErrorResponse(errors));
	}
}
