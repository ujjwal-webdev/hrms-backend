package com.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sdp.exception.EmployeeException;
import com.sdp.exception.SkillException;
import com.sdp.model.Employee;
import com.sdp.model.Skill;
import com.sdp.repository.EmployeeRepository;
import com.sdp.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private SkillRepository skillRepo;
	
	@Override
	public Skill addSkill(Skill skill) throws EmployeeException {
		
      	Integer empId = getEmployee().getEmployeeId();
		
		
		Employee employee = employeeRepo.findById(empId)
									    .orElseThrow(() -> new EmployeeException("employee does not exist with this id"));
		
		employee.getSkills().add(skill);
		skill.setEmployee(employee);
		
		return skillRepo.save(skill);
		
	}

	@Override
	public List<Skill> getAllSkills() throws SkillException, EmployeeException {
		
		Integer empId = getEmployee().getEmployeeId();
		
		Employee employee = employeeRepo.findById(empId)
			    						.orElseThrow(() -> new EmployeeException("employee does not exist with this id"));

		List<Skill> skills = employee.getSkills();
		
		if(skills.isEmpty())
				throw new SkillException("No skill found...");
		else return skills;
	}
	
	@Override
	public Employee getEmployee() {
		
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails userDetails = (UserDetails)o;
		
		String username = userDetails.getUsername();
		
		return employeeRepo.findByUserName(username).orElseThrow(() -> new RuntimeException("user does not exist"));
	}

}
