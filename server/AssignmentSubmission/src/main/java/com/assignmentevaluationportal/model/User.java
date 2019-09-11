package com.assignmentevaluationportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.assignmentevaluationportal.constants.UserStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "user")
public class User extends BaseEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String email;
	
	@Column(name =  "phone_no", nullable = false)
	private String phoneNo;
	
	@Column(nullable = false)
	private String password;
	
	private String avatarUrl;
	
	private UserStatus status;
}
