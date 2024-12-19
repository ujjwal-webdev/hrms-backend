package com.sdp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdp.Enum.EmployeeOrAdmin;
import com.sdp.Enum.Gender;
import com.sdp.Enum.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Integer employeeId;
	
	private String name;
	
	@JsonIgnore
	private String userName;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	private EmployeeOrAdmin EmployeeOrAdmin;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private Double salary;
	private String phoneNo;
	private LocalDate dateOfBirth;
		
	@CreationTimestamp
	@Column(updatable = false)
	@JsonIgnore
	private LocalDate joiningDate;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "employee")
	@JsonIgnore
	private List<Address> addresses = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonIgnore
	private List<EducationBackground> educationBackground = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonIgnore
	private List<WorkExperience> workExperience = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonIgnore
	private List<Equipment> equipments = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonIgnore
	private List<Skill> skills = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonIgnore
	private List<Salary> salaries = new ArrayList<>();
	
	@ManyToOne
	@JsonIgnore
	private Department department;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "employee",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Leave> leaves = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "employees")
	@JsonIgnore
	private List<Work> works = new ArrayList<>();	
}










