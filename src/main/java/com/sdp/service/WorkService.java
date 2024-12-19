package com.sdp.service;

import java.util.List;

import com.sdp.dto.GroupWorkDto;
import com.sdp.dto.WorkDto;
import com.sdp.exception.EmployeeException;
import com.sdp.exception.WorkException;
import com.sdp.model.Employee;
import com.sdp.model.Work;

public interface WorkService {

	public String assignWork(Integer empId,Work work) throws EmployeeException;
	
	public WorkDto deleteWork(Integer workId) throws WorkException;
	
	public WorkDto updateWork(Integer workId,Work work) throws WorkException;
	
	public String groupWork(GroupWorkDto dto) throws EmployeeException;
	
	public String changeStatusToCompleted(Integer workId) throws EmployeeException, WorkException;
	
	public Employee getEmployee();
	
	public List<WorkDto> getAllEmployeeWork() throws EmployeeException,WorkException;
	
	public List<WorkDto> getAllWorks() throws WorkException;
	
	public List<WorkDto> getAllIndividualWorks() throws WorkException;
	
	public List<WorkDto> getAllGroupWorks() throws WorkException;
	
	public List<WorkDto> getAllPendingWorks() throws WorkException;
	
	public List<WorkDto> getAllIndividualPendingWorks() throws WorkException;
	
	public List<WorkDto> getAllGroupPendingWorks() throws WorkException;
	
	public List<WorkDto> getAllCompletedWorks() throws WorkException;
	
	public List<WorkDto> getAllIndividualCompletedWorks() throws WorkException;
	
	public List<WorkDto> getAllGroupCompletedWorks() throws WorkException;
	
	public List<WorkDto> getAllInCompletedWorks() throws WorkException;
	
	public List<WorkDto> getAllIndividualInCompletedWorks() throws WorkException;
	
	public List<WorkDto> getAllGroupInCompletedWorks() throws WorkException;
}
