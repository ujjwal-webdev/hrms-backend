package com.sdp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer salaryId;
	
	private Integer year;
	private Double salary;
	private Double bonusThatYear;
	private Double benefitPoints;
	
	@ManyToOne
	@JsonIgnore
	private Employee employee;	
}
