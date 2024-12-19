package com.sdp.service;

import java.util.List;

import com.sdp.exception.EducationException;
import com.sdp.exception.EmployeeException;
import com.sdp.model.EducationBackground;
import com.sdp.model.Employee;

public interface EducationService {

	public Employee getEmployee();
	
	public EducationBackground addEducation(EducationBackground educationBackground) throws EmployeeException;
	
	public List<EducationBackground> getAllEducations() throws EducationException,EmployeeException;
}
