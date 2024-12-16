package com.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sdp.exception.EmployeeException;
import com.sdp.exception.WorkExperienceException;
import com.sdp.model.Employee;
import com.sdp.model.WorkExperience;
import com.sdp.repository.EmployeeRepository;
import com.sdp.repository.WorkExperienceRepository;

@Service
public class WorkExperienceImpl implements WorkExperienceService{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private WorkExperienceRepository workExperienceRepo;
	
	// this method will add the address.
	
	@Override
	public WorkExperience addWorkExperience(WorkExperience workExperience) throws EmployeeException {
		
      	Integer empId = getEmployee().getEmployeeId();
		
		
		Employee employee = employeeRepo.findById(empId)
									    .orElseThrow(() -> new EmployeeException("employee does not exist with this id"));
		
		employee.getWorkExperience().add(workExperience);
		workExperience.setEmployee(employee);
		
		return workExperienceRepo.save(workExperience);
		
	}

	@Override
	public List<WorkExperience> getAllWorkExperiences() throws WorkExperienceException, EmployeeException {
		
		Integer empId = getEmployee().getEmployeeId();
		
		Employee employee = employeeRepo.findById(empId)
			    						.orElseThrow(() -> new EmployeeException("employee does not exist with this id"));

		List<WorkExperience> workExperience = employee.getWorkExperience();
		
		if(workExperience.isEmpty())
				throw new WorkExperienceException("No work experience found...");
		else return workExperience;
	}

//	@Override
//	public Address updateAddress(Integer addressId, Address address)
//			throws AddressException, EmployeeException {
//		
//		Integer empId = getEmployee().getEmployeeId();
//		
//		Employee employee = employeeRepo.findById(empId)
//										.orElseThrow(() -> new EmployeeException("employee does not exist with this id"));
//
//		// for checking whether this address present in the database or not
//		 addressRepo.findById(addressId).orElseThrow(() -> new AddressException("no address found with this id"));
//		
//		 // for checking whether this address is associated with this address or not
//		List<Address> addresses = employee.getAddresses()
//										  .stream()
//										  .filter((add) -> add.getAddressId() == addressId)
//										  .collect(Collectors.toList());
//		
//		if(addresses.isEmpty())
//				throw new AddressException("no address found with this id");
//		
//		
//		address.setAddressId(addresses.get(0).getAddressId());
//		
//		address.setEmployee(employee);
//		
//		return addressRepo.save(address);
//		
//	}

//	@Override
//	@Transactional
//	public Address deleteAddress(Integer addressId) throws AddressException, EmployeeException {
//		
//		Integer empId = getEmployee().getEmployeeId();
//		
//		Employee employee = employeeRepo.findById(empId)
//										.orElseThrow(() -> new EmployeeException("employee does not exist with this id"));
//
//		 addressRepo.findById(addressId).orElseThrow(() -> new AddressException("no address found with this id"));
//
//		 // for checking whether this address is associated with this address or not
//		List<Address> addresses = employee.getAddresses()
//										  .stream()
//										  .filter((add) -> add.getAddressId() == addressId)
//										  .collect(Collectors.toList());
//		
//		if(addresses.isEmpty())
//				throw new AddressException("No address found with this Id");
//		
//		Address addressObj = addresses.get(0);
//		
//		addressRepo.myDeleteMethod(addressObj.getAddressId());
//		
//		return addressObj;
//	}
	
	
	
	@Override
	public Employee getEmployee() {
		
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails userDetails = (UserDetails)o;
		
		String username = userDetails.getUsername();
		
		return employeeRepo.findByUserName(username).orElseThrow(() -> new RuntimeException("user does not exist")); 
		
		
	}

}
