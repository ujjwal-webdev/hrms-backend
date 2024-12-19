package com.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sdp.exception.AddressException;
import com.sdp.exception.EmployeeException;
import com.sdp.exception.SalaryException;
import com.sdp.model.Employee;
import com.sdp.model.Salary;
import com.sdp.repository.EmployeeRepository;
import com.sdp.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private SalaryRepository salaryRepo;

	@Override
	public List<Salary> getSalariesByEmployeeId(Integer employeeId) throws SalaryException, EmployeeException
	{
		Employee employee = employeeRepo.findById(employeeId)
			    						.orElseThrow(() -> new EmployeeException("employee does not exist with this id"));

		List<Salary> salaries = employee.getSalaries();
		
		if(salaries.isEmpty())
				throw new SalaryException("No salary found...");
		else return salaries;
	}
	
	
	@Override
	public Employee getEmployee() {
		
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails userDetails = (UserDetails)o;
		
		String username = userDetails.getUsername();
		
		return employeeRepo.findByUserName(username).orElseThrow(() -> new RuntimeException("user does not exist")); 

	}
}
