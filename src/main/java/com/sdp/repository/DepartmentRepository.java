package com.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdp.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	public Department findByDepartmentName(String deptName);
}
