package com.assignmentevaluationportal.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

	@Column(name = "created_ts", nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp createdTs; 
	
	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;
	
	@Column(name = "modified_ts")
	@UpdateTimestamp
	private Timestamp modifiedTs;
	
	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;
	
	@Version
	private Integer version;
}
