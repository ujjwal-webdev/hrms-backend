package com.sdp.service;

import java.util.List;

import com.sdp.Enum.Role;
import com.sdp.dto.AddEmployeeDto;
import com.sdp.dto.AdminDto;
import com.sdp.dto.AuthenticatedResponseDto;
import com.sdp.dto.GetEmployeeDto;
import com.sdp.dto.LoginDto;
import com.sdp.dto.UpdateEmployeeDto;
import com.sdp.dto.UpdatePasswordDto;
import com.sdp.exception.DepartmentException;
import com.sdp.exception.EmployeeException;
import com.sdp.exception.PasswordException;
import com.sdp.model.Employee;

public interface EmployeeService {
	
	public GetEmployeeDto viewProfile();
	
	public Employee getEmployee();
	
	public AdminDto viewProfileAdmin();
	
	public AdminDto registerAdmin(Employee admin);
	
	public GetEmployeeDto updateEmployee(UpdateEmployeeDto dto);
	
	public String updatePassword(UpdatePasswordDto dto) throws PasswordException;

	public AddEmployeeDto addEmployee(Integer departmentId,Employee employee) throws DepartmentException;
	
	public AuthenticatedResponseDto login(LoginDto loginDto);
	
	public GetEmployeeDto getEmployeeByEmpId(Integer employeeId) throws EmployeeException;
	
	public GetEmployeeDto getEmployeeByEmpUserName(String userName) throws EmployeeException;
	
	public List<GetEmployeeDto> getAllEmployees() throws EmployeeException;
	
	public GetEmployeeDto changeEmployeeRole(Integer employeeId,Role newRole) throws EmployeeException;
	
	public GetEmployeeDto changeEmployeeSalary(Integer employeeId,Double newSalary, Double bonusThatYear, Double benefitPoints) throws EmployeeException;
	
	public GetEmployeeDto changeEmployeeDepartment(Integer employeeId,Integer newDepartmentId) throws EmployeeException,DepartmentException;
	
	public GetEmployeeDto deleteEmployee(Integer employeeId) throws EmployeeException;

}
