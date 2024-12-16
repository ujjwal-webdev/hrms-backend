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
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer skillId;
	
	private String name;
	
	private String proficiency;
	
	private Integer yearsOfExperience;
	
	@ManyToOne
	@JsonIgnore
	private Employee employee;
}
