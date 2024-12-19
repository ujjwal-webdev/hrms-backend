package com.sdp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdp.dto.AuthenticatedResponseDto;
import com.sdp.dto.GetEmployeeDto;
import com.sdp.dto.LeaveDto;
import com.sdp.dto.LoginDto;
import com.sdp.dto.UpdateEmployeeDto;
import com.sdp.dto.UpdatePasswordDto;
import com.sdp.dto.WorkDto;
import com.sdp.exception.AddressException;
import com.sdp.exception.EmployeeException;
import com.sdp.exception.LeaveException;
import com.sdp.exception.PasswordException;
import com.sdp.exception.WorkException;
import com.sdp.model.Address;
import com.sdp.model.EducationBackground;
import com.sdp.model.Leave;
import com.sdp.model.Skill;
import com.sdp.model.WorkExperience;
import com.sdp.service.AddressService;
import com.sdp.service.EducationService;
import com.sdp.service.EmployeeService;
import com.sdp.service.LeaveService;
import com.sdp.service.SkillService;
import com.sdp.service.WorkExperienceService;
import com.sdp.service.WorkService;

@CrossOrigin("*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private EducationService educationService;
	
	@Autowired
	private WorkExperienceService workExperienceService;
	
	@Autowired
	private SkillService skillService;

	/*
	 * Employee Login API
	 */

	// Employee login API
	@PostMapping("/login")
	public ResponseEntity<AuthenticatedResponseDto> login(@Valid @RequestBody LoginDto loginDto)
	{
		AuthenticatedResponseDto authenticatedResponseDto = employeeService.login(loginDto);
		
		return new ResponseEntity<AuthenticatedResponseDto>(authenticatedResponseDto,HttpStatus.OK);
	}
	
	/*
	 * Profile related APIs
	 */

	// Employee View Profile API
	@GetMapping("/viewProfile")
	public ResponseEntity<GetEmployeeDto> viewProfile()
	{
		GetEmployeeDto getEmployeeDto = employeeService.viewProfile();

		return new ResponseEntity<GetEmployeeDto>(getEmployeeDto, HttpStatus.OK);
	}

	// Update profile API
	@PutMapping("/updateProfile")
	public ResponseEntity<GetEmployeeDto> updateProfile(@RequestBody UpdateEmployeeDto dto)
	{
		GetEmployeeDto getEmployeeDto = employeeService.updateEmployee(dto);

		return new ResponseEntity<GetEmployeeDto>(getEmployeeDto, HttpStatus.OK);
	}

	// Update password API
	@PutMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordDto dto) throws PasswordException
	{
		String message = employeeService.updatePassword(dto);
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	/*
	 * Address related resource
	 */

	// Add address API
	@PostMapping("/address")
	public ResponseEntity<Address> addAdressHandler(@Valid @RequestBody Address address) throws EmployeeException
	{
		Address addressObj = addressService.addAddress(address);
		
		return new ResponseEntity<Address>(addressObj,HttpStatus.CREATED);
	}

	// Update address API
	@PutMapping("/address/{addressId}")
	public ResponseEntity<Address> updadteAdressHandler(@PathVariable("addressId") Integer addressId,@RequestBody Address address) throws EmployeeException, AddressException
	{
		Address addressObj = addressService.updateAddress(addressId, address);
		
		return new ResponseEntity<Address>(addressObj,HttpStatus.CREATED);
	}
	
	// Get all addresses API
	@GetMapping("/address")
	public ResponseEntity<List<Address>> getAdressesHandler() throws EmployeeException, AddressException
	{
		List<Address> addresses = addressService.getAllAddresses();
		
		return new ResponseEntity<List<Address>>(addresses,HttpStatus.CREATED);
	}

	// Remove address API
	@DeleteMapping("/address/{addressId}")
	public ResponseEntity<Address> deleteAdressHandler(@PathVariable("addressId") Integer addressId) throws EmployeeException, AddressException
	{
		Address addressObj = addressService.deleteAddress(addressId);
		
		return new ResponseEntity<Address>(addressObj,HttpStatus.CREATED);
	}
	
	/*
	 * Education Background API
	 */

	// Add education API
	@PostMapping("/education")
	public ResponseEntity<EducationBackground> addEducationHandler(@Valid @RequestBody EducationBackground educationBackground) throws EmployeeException
	{
		EducationBackground educationObj = educationService.addEducation(educationBackground);
		
		return new ResponseEntity<EducationBackground>(educationObj,HttpStatus.CREATED);
	}
	
	/*
	 * Work Experience API
	 */

	// Add work experience API
	@PostMapping("/work-experience")
	public ResponseEntity<WorkExperience> addWorkExperienceHandler(@Valid @RequestBody WorkExperience workExperience) throws EmployeeException
	{
		WorkExperience workExperienceObj = workExperienceService.addWorkExperience(workExperience);
		
		return new ResponseEntity<WorkExperience>(workExperienceObj,HttpStatus.CREATED);
	}
	
	/*
	 * Skill API
	 */

	// Add skills API
	@PostMapping("/skills")
	public ResponseEntity<Skill> addSkillHandler(@Valid @RequestBody Skill skill) throws EmployeeException
	{
		Skill skillObj = skillService.addSkill(skill);
		
		return new ResponseEntity<Skill>(skillObj,HttpStatus.CREATED);
	}
	
	/*
	 * Leave Related APIs
	 */

	// Add leave API
	@PostMapping("/leave")
	public ResponseEntity<LeaveDto> addLeaveHandler(@RequestBody Leave leave) throws EmployeeException, LeaveException
	{
		LeaveDto leaveDto = leaveService.addLeave(leave);
		
		return new ResponseEntity<LeaveDto>(leaveDto, HttpStatus.CREATED);
	}
	
	// Update leave API
	@PutMapping("/leave")
	public ResponseEntity<LeaveDto> updateLeaveHandler(@RequestBody Leave leave) throws EmployeeException, LeaveException
	{
		LeaveDto leaveDto = leaveService.updateLeave(leave);
		
		return new ResponseEntity<LeaveDto>(leaveDto, HttpStatus.CREATED);
	}
	
	// Remove leave API
	@DeleteMapping("/leave")
	public ResponseEntity<LeaveDto> deleteLeaveHandler() throws EmployeeException, LeaveException
	{
		LeaveDto leaveDto = leaveService.deletePendingLeave();
		
		return new ResponseEntity<LeaveDto>(leaveDto, HttpStatus.CREATED);
	}
	
	// Get Leave status API
	@GetMapping("/leave")
	public ResponseEntity<LeaveDto> getRecentLeaveStatusHandler() throws EmployeeException, LeaveException
	{
		LeaveDto leaveDto = leaveService.checkLatestLeaveStatus();
		
		return new ResponseEntity<LeaveDto>(leaveDto, HttpStatus.CREATED);
	}

	// Get all leaves API
	@GetMapping("/allLeaves")
	public ResponseEntity<List<LeaveDto>> getAllLeaves() throws EmployeeException,LeaveException
	{
		List<LeaveDto> dtos = leaveService.getAllLeaves();
		
		return new ResponseEntity<List<LeaveDto>>(dtos,HttpStatus.OK);
	}
	
	/*
	 * work related Resources
	 */

	// Get all projects API
	@GetMapping("/works")
	public ResponseEntity<List<WorkDto>> getAllWorks() throws EmployeeException,WorkException
	{
		List<WorkDto> dtos = workService.getAllEmployeeWork();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Change work status API
	@PutMapping("/changeStatus/{id}")
	public ResponseEntity<String> changeWorkStatus(@PathVariable("id") Integer workId) throws EmployeeException,WorkException
	{
		String str = workService.changeStatusToCompleted(workId);
		
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
}

