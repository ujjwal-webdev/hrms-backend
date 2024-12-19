package com.sdp.service;

import java.util.List;

import com.sdp.exception.EmployeeException;
import com.sdp.exception.SkillException;
import com.sdp.model.Employee;
import com.sdp.model.Skill;

public interface SkillService {

	public Employee getEmployee();
	
	public Skill addSkill(Skill skill) throws EmployeeException;
	
	public List<Skill> getAllSkills() throws SkillException,EmployeeException;
}
