package com.sdp.service;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sdp.Enum.EmployeeOrAdmin;
import com.sdp.dto.EquipmentDto;
import com.sdp.exception.EmployeeException;
import com.sdp.exception.EquipmentException;
import com.sdp.exception.SalaryException;
import com.sdp.model.Employee;
import com.sdp.model.Equipment;
import com.sdp.model.Salary;
import com.sdp.repository.EmployeeRepository;
import com.sdp.repository.EquipmentRepository;

@Service
public class EquipmentServiceImpl implements EquipmentService{

	@Autowired
	private EquipmentRepository equipmentRepo;

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public String assignEquipment(Integer empId, Equipment equipment) throws EmployeeException {
		
		Employee employee = employeeRepo.findById(empId).orElseThrow(() -> new EmployeeException("employee does not exist with this Id"));
		
		// In single table we are storing both admin and employee that's why checking what is the role of employee
		if(employee.getEmployeeOrAdmin() !=  EmployeeOrAdmin.EMPLOYEE)
					throw new EmployeeException("employee does not exist with this Id");

		
		employee.getEquipments().add(equipment);
		equipment.setEmployee(employee);
		
		equipmentRepo.save(equipment);
		
		return "equipment has been assigned";
	}


	@Override
	public EquipmentDto deleteEquipment(Integer equipmentId) throws EquipmentException {
	
		Equipment equipment = equipmentRepo.findById(equipmentId).orElseThrow(() -> new EquipmentException("No equipment present with this ID"));
		
		equipmentRepo.delete(equipment);
		
		return modelMapper.map(equipment, EquipmentDto.class);
	}
	
	@Override
	public List<Equipment> getEquipmentsByEmployeeId(Integer employeeId) throws EquipmentException, EmployeeException {
		
		Employee employee = employeeRepo.findById(employeeId)
			    						.orElseThrow(() -> new EmployeeException("employee does not exist with this id"));

		List<Equipment> equipments = employee.getEquipments();
		
		if(equipments.isEmpty())
				throw new EquipmentException("No equipment found...");
		else return equipments;
	}

	
	@Override
	public Employee getEmployee() {
		
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails userDetails = (UserDetails)o;
		
		String username = userDetails.getUsername();
		
		return employeeRepo.findByUserName(username).orElseThrow(() -> new RuntimeException("user does not exist")); 
		
		
	}

}
