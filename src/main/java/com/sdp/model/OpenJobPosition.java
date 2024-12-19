package com.sdp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class OpenJobPosition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer jobId;

	private String description;
	private String position;
	private String experienceRequired;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "openJobPosition")
	@JsonIgnore
	private List<JobCandidate> jobCandidates = new ArrayList<>();
}
