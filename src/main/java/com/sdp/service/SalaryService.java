package com.sdp.service;

import java.util.List;

import com.sdp.exception.EmployeeException;
import com.sdp.exception.SalaryException;
import com.sdp.model.Employee;
import com.sdp.model.Salary;

public interface SalaryService {

	public Employee getEmployee();
	
	public List<Salary> getSalariesByEmployeeId(Integer employeeId) throws SalaryException, EmployeeException;
}
