package com.sdp.service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdp.dto.DepartmentDto;
import com.sdp.exception.DepartmentException;
import com.sdp.model.Department;
import com.sdp.repository.DepartmentRepository;

//@Entity
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepo;
	
	// for converting one object into other i am using modelmapper It will reduce the code.
	@Autowired
	private ModelMapper modelMapper;
	
	
	// this method will add new department 
	
	@Override
	public DepartmentDto addDepartment(Department department) throws DepartmentException{
		
		 List<Department> list = departmentRepo.findAll()
				 							   .stream().filter((dept) -> dept.getDepartmentName().toLowerCase().equals(department.getDepartmentName().toLowerCase()))
				 							   .collect(Collectors.toList());
		
		
		 
		if(list.size() > 0) throw new DepartmentException("Department already exist with this name");
		
		Department depObj = departmentRepo.save(department);
		
		return modelMapper.map(depObj, DepartmentDto.class);
		
	}

	// this method will update existing department.
	
	@Override
	public DepartmentDto updateDepartmentName(Integer deptId, Department department) throws DepartmentException {
		
		 departmentRepo.findById(deptId)
					   .orElseThrow(() -> new DepartmentException("No department exist with this department id"));
		
		department.setDepartmentId(deptId);
		Department updatedDepartment = departmentRepo.save(department);
		return modelMapper.map(updatedDepartment, DepartmentDto.class);
		
	}

	// this method will delete the existing department
	
	@Override
	public DepartmentDto deleteDepartment(Integer deptId) throws DepartmentException {
		
		Department department = departmentRepo.findById(deptId)
											  .orElseThrow(() -> new DepartmentException("No department exist with this department id"));
		
		departmentRepo.delete(department);
		return modelMapper.map(department,DepartmentDto.class);
		
	}

	// this method will return the department it will find department with the help of primary key(departmentId).
	
	@Override
	public DepartmentDto getDepartmentById(Integer deptId) throws DepartmentException {
		
		Department department = departmentRepo.findById(deptId)
				                              .orElseThrow(() -> new DepartmentException("No department exist with this department id"));
		
		return modelMapper.map(department,DepartmentDto.class);
		
	}

	// this method will return the all departments which are present in the database
	
	@Override
	public List<DepartmentDto> getAllDepartments() throws DepartmentException {
		
		List<Department> departments = departmentRepo.findAll();
		
		if(departments.isEmpty())
				throw new DepartmentException("No department present in the database");
		else {
			
			List<DepartmentDto> list = new ArrayList<>();
			for(Department department : departments) {
				list.add(modelMapper.map(department, DepartmentDto.class));
			}
			return list;
		}
			
	}

	// this method will returns department it will find the department with the help of departmentName 
	
	@Override
	public DepartmentDto getDepartmentByName(String deptName) throws DepartmentException {
		
		Department departments = departmentRepo.findByDepartmentName(deptName);
		
		if(departments == null)
				throw new DepartmentException("no department present with this name");
		else {
			return modelMapper.map(departments, DepartmentDto.class);
		}
		
	}

	// this function will return the departments after sorting them in Alphabatical order by name 
	
	@Override
	public List<DepartmentDto> getDepartmentsInAlphabaticalOrder() throws DepartmentException {
		
		List<Department> departments = departmentRepo.findAll();
		
		if(departments.isEmpty())
				throw new DepartmentException("zero department found...");
		else {
			
			List<DepartmentDto> list = new ArrayList<>();
			
			for(Department department : departments) {
				DepartmentDto dto = modelMapper.map(department, DepartmentDto.class);
				list.add(dto);
			}
			
			Collections.sort(list, (d1,d2) -> d1.getDepartmentName().compareToIgnoreCase(d2.getDepartmentName()));
			
			return list;
		}
	}
		
	// this function will return the departments after sorting them in reverse Alphabatical order by name	

	@Override
	public List<DepartmentDto> getDepartmentsInReverseAlphabaticalOrder() throws DepartmentException {
		
		List<Department> departments = departmentRepo.findAll();
		
		if(departments.isEmpty())
				throw new DepartmentException("zero department found...");
		else {
			
			List<DepartmentDto> list = new ArrayList<>();
			
			for(Department department : departments) {
				DepartmentDto dto = modelMapper.map(department, DepartmentDto.class);
				list.add(dto);
			}
			
			Collections.sort(list, (d1,d2) -> d2.getDepartmentName().compareToIgnoreCase(d1.getDepartmentName()));
			
			return list;
		}
		
		
		
	}
		
		
		
	
	
	
	
	
}
