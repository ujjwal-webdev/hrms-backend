package com.sdp.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationBackground {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer educationBgId;
	
	private String instituteName;
	private String qualification;
	private String major;
	
	@ManyToOne
	@JsonIgnore
	private Employee employee;
}
