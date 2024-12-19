package com.sdp.service;

import java.util.List;

import com.sdp.dto.EquipmentDto;
import com.sdp.exception.EmployeeException;
import com.sdp.exception.EquipmentException;
import com.sdp.exception.SalaryException;
import com.sdp.model.Employee;
import com.sdp.model.Equipment;

public interface EquipmentService {

	public String assignEquipment(Integer empId,Equipment equipment) throws EmployeeException;
	
	public EquipmentDto deleteEquipment(Integer equipmentId) throws EquipmentException;
	
	public Employee getEmployee();

	public List<Equipment> getEquipmentsByEmployeeId(Integer employeeId) throws EquipmentException, EmployeeException;;
}
