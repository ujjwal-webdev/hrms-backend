package com.sdp.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdp.Enum.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobCandidate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer jobCandidateId;
	
	private String firstName;
	private String lastName;
	private Integer age;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private String phoneNo;
	private LocalDate dateOfBirth;

	@NotNull
	private String email;
	
	@NotNull
	private String userName;

	@NotNull
	@Size(max = 100)
	private String password;
	
	@NotNull
	private String workCompany1;
	
	@NotNull
	private String workCompany1Skills;
	
	@NotNull
	@Size(max = 1000)
	private String workCompany1Description;
	
	private String workCompany2;
	private String workCompany2Skills;
	private String workCompany2Description;
	private String education1;
	
	@NotNull
	@Size(max = 1000)
	private String education1Description;
	
	private String education2;
	
	@NotNull
	@Size(max = 1000)
	private String education2Description;
	
	private String status;
	
	private String address;
	
	@ManyToOne
	@JsonIgnore
	private OpenJobPosition openJobPosition;
}
