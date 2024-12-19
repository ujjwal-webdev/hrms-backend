package com.sdp.service;

import java.util.List;

import com.sdp.exception.EmployeeException;
import com.sdp.exception.WorkExperienceException;
import com.sdp.model.Employee;
import com.sdp.model.WorkExperience;

public interface WorkExperienceService {

	public Employee getEmployee();
	
	public WorkExperience addWorkExperience(WorkExperience workExperience) throws EmployeeException;
	
	public List<WorkExperience> getAllWorkExperiences() throws WorkExperienceException,EmployeeException;
}
