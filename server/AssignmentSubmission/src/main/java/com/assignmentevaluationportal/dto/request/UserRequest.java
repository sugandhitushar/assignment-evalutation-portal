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
	
	@NotBlank(message = "INVALID_FIRST_NAME")
	private String firstName;
	
	@NotBlank(message = "INVALID_LAST_NAME")
	private String lastName;
	
	@NotBlank(message = "INVALID_EMAIL_ID")
	@Email(message = "INVALID_EMAIL_ID")
	private String email;
	
	@NotBlank(message = "INVALID_PHONE_NO")
	private String phoneNo;
	
	@NotBlank(message = "INVALID_PASSWORD")
	private String password;
	
	private String avatarUrl;
	
	@NotNull(message = "INVALID_GENDER")
	private Gender gender;
}
