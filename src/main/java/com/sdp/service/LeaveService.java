package com.sdp.service;

import java.util.List;

import com.sdp.Enum.LeaveStatus;
import com.sdp.dto.LeaveDto;
import com.sdp.exception.EmployeeException;
import com.sdp.exception.LeaveException;
import com.sdp.model.Employee;
import com.sdp.model.Leave;

public interface LeaveService {
	
	public Employee getEmployee();
	
	public LeaveDto addLeave(Leave leave) throws EmployeeException,LeaveException;
	
	public LeaveDto updateLeave(Leave leave) throws EmployeeException, LeaveException;
	
	public LeaveDto deletePendingLeave() throws EmployeeException, LeaveException;

	public LeaveDto checkLatestLeaveStatus() throws EmployeeException, LeaveException;
	
	public List<LeaveDto> getAllLeaves() throws EmployeeException,LeaveException;
	
	public List<LeaveDto> getAllLeavesHistory() throws LeaveException;
	
	public List<LeaveDto> getLeavesOfParticularEmployee(Integer empId) throws LeaveException,EmployeeException;
	
	public List<LeaveDto> getPendingLeaves() throws LeaveException;
	
	public LeaveDto responseToLeave(Integer leaveId,LeaveStatus status) throws LeaveException;
	
}
