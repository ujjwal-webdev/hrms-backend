package com.sdp.service;

import java.util.List;

import com.sdp.exception.EmployeeException;
import com.sdp.exception.SalaryException;
import com.sdp.model.Employee;
import com.sdp.model.Salary;

public interface SalaryService {

	public Employee getEmployee();
	
//	public Address addAddress(Address address) throws EmployeeException;
	
//	public List<Address> getAllAddresses() throws AddressException,EmployeeException;
//	
//	public Address updateAddress(Integer addressId ,Address address) throws AddressException,EmployeeException;
//	
//	public Address deleteAddress(Integer addressId) throws AddressException,EmployeeException;
	
	public List<Salary> getSalariesByEmployeeId(Integer employeeId) throws SalaryException, EmployeeException;

//	public List<Salary> getSalariesByEmployeeId() throws SalaryException, EmployeeException;
}
