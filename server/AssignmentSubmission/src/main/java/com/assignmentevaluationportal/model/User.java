package com.assignmentevaluationportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.assignmentevaluationportal.constants.Gender;
import com.assignmentevaluationportal.constants.UserStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	private Gender gender;

	public User(String firstName, String lastName, String email, String phoneNo, String password, String avatarUrl,
			UserStatus status, Gender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.password = password;
		this.avatarUrl = avatarUrl;
		this.status = status;
		this.gender = gender;
	}
}
