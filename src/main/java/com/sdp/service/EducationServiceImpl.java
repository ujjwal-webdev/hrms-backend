package com.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sdp.exception.AddressException;
import com.sdp.exception.EducationException;
import com.sdp.exception.EmployeeException;
import com.sdp.model.EducationBackground;
import com.sdp.model.Employee;
import com.sdp.repository.EducationRepository;
import com.sdp.repository.EmployeeRepository;

@Service
public class EducationServiceImpl implements EducationService{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private EducationRepository educationRepo;
	
	// this method will add the education.
	
	@Override
	public EducationBackground addEducation(EducationBackground educationBackground) throws EmployeeException {
		
      	Integer empId = getEmployee().getEmployeeId();
		
		
		Employee employee = employeeRepo.findById(empId)
									    .orElseThrow(() -> new EmployeeException("employee does not exist with this id"));
		
		employee.getEducationBackground().add(educationBackground);
		educationBackground.setEmployee(employee);
		
		return educationRepo.save(educationBackground);
		
	}

	@Override
	public List<EducationBackground> getAllEducations() throws EducationException, EmployeeException {
		
		Integer empId = getEmployee().getEmployeeId();
		
		Employee employee = employeeRepo.findById(empId)
			    						.orElseThrow(() -> new EmployeeException("employee does not exist with this id"));

		List<EducationBackground> educationBackground = employee.getEducationBackground();
		
		if(educationBackground.isEmpty())
				throw new EducationException("No education found...");
		else return educationBackground;
	}
	
	@Override
	public Employee getEmployee() {
		
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails userDetails = (UserDetails)o;
		
		String username = userDetails.getUsername();
		
		return employeeRepo.findByUserName(username).orElseThrow(() -> new RuntimeException("user does not exist")); 
		
		
	}

}
