package com.assignmentevaluationportal.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

	@Column(name =  "created_date", nullable = false, updatable = false)
	@CreatedDate
	private long createdDate; 
	
	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;
	
	@Column(name = "modified_date")
	@LastModifiedDate
	private long modifiedDate;
	
	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;
	
	@Version
	private Integer version;
}
