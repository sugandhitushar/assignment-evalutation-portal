package com.assignmentevaluationportal.dto.response;

import com.assignmentevaluationportal.constants.Gender;
import com.assignmentevaluationportal.constants.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phoneNo;
	
	private String avatarUrl;
	
	private Gender gender;
	
	private UserStatus status;
}
