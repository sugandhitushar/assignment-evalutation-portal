package com.assignmentevaluationportal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingleErrorResponse {
	
	Integer code;
	String message;

}
