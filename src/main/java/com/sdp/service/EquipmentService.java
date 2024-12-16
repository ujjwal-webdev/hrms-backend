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
	
//	public WorkDto updateWork(Integer workId,Work work) throws WorkException;
	
//	public String groupWork(GroupWorkDto dto) throws EmployeeException;
	
//	public String changeStatusToCompleted(Integer workId) throws EmployeeException, WorkException;
	
	public Employee getEmployee();

	public List<Equipment> getEquipmentsByEmployeeId(Integer employeeId) throws EquipmentException, EmployeeException;;
	
//	public List<WorkDto> getAllEmployeeWork() throws EmployeeException,WorkException;
	
//	public List<WorkDto> getAllWorks() throws WorkException;
	
//	public List<WorkDto> getAllIndividualWorks() throws WorkException;
	
//	public List<WorkDto> getAllGroupWorks() throws WorkException;
	
//	public List<WorkDto> getAllPendingWorks() throws WorkException;
	
//	public List<WorkDto> getAllIndividualPendingWorks() throws WorkException;
//	
//	public List<WorkDto> getAllGroupPendingWorks() throws WorkException;
//	
//	public List<WorkDto> getAllCompletedWorks() throws WorkException;
//	
//	public List<WorkDto> getAllIndividualCompletedWorks() throws WorkException;
//	
//	public List<WorkDto> getAllGroupCompletedWorks() throws WorkException;
//	
//	public List<WorkDto> getAllInCompletedWorks() throws WorkException;
//	
//	public List<WorkDto> getAllIndividualInCompletedWorks() throws WorkException;
//	
//	public List<WorkDto> getAllGroupInCompletedWorks() throws WorkException;
	
	
}
