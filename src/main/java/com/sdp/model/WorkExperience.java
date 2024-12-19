package com.sdp.model;

import java.time.LocalDate;

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
public class WorkExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer workExpId;
	
	private String companyName;
	private String designation;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;
	private String skillsUsed;
	
	@ManyToOne
	@JsonIgnore
	private Employee employee;
}
