package com.assignmentevaluationportal.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.assignmentevaluationportal.constants.Gender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String phoneNo;
	
	@NotBlank
	private String password;
	
	private String avatarUrl;
	
	@NotNull
	private Gender gender;
}
