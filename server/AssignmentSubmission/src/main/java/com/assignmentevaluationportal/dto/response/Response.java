package com.assignmentevaluationportal.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.assignmentevaluationportal.constants.AEPError;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response<T> {
	
	T data;
	List<SingleErrorResponse> errors = new ArrayList<>();
	
	public static <T> Response<T> getSuccessResponse(T data) {
		Response<T> response = new Response<>();
		response.data = data;
		response.errors = null;
		return response;
	}
	
	public static Response<Void> getErrorResponse(List<AEPError> errors) {
		Response<Void> response = new Response<>();
		errors.stream().forEach(error -> {
			response.errors.add(new SingleErrorResponse(error.getErrorCode(), error.getErrorMessage()));
		});
		return response;
	}
}
