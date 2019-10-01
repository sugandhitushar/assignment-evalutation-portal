package com.assignmentevaluationportal.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private String accessToken;

    private String refreshToken;
    
	public JwtResponse(String accessToken) {
		this.accessToken = accessToken;
	}
    
}