package com.assignmentevaluationportal.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.assignmentevaluationportal.constants.AEPError;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AEPException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	List<AEPError> errors = new ArrayList<>();
	
	public AEPException(HttpStatus status, AEPError error) {
		this.errors.add(error);
		this.status = status;
	}
	
	public AEPException(AEPError error) {
		this.errors.add(error);
	}
}
