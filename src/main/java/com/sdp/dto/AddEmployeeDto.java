package com.sdp.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sdp.Enum.EmployeeOrAdmin;
import com.sdp.Enum.Gender;
import com.sdp.Enum.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeDto {
	
	private Integer employeeId;
	private String name;
	private String userName;
	private String email;
	private String phoneNo;

	@Enumerated(EnumType.STRING)
	private EmployeeOrAdmin EmployeeOrAdmin;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private Double salary;
	private LocalDate dateOfBirth;
	private LocalDate joiningDate;
}
