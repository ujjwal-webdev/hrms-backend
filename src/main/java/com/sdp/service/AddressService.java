package com.sdp.service;

import java.util.List;

import com.sdp.exception.AddressException;
import com.sdp.exception.EmployeeException;
import com.sdp.model.Address;
import com.sdp.model.Employee;

public interface AddressService {

	public Employee getEmployee();
	
	public Address addAddress(Address address) throws EmployeeException;
	
	public List<Address> getAllAddresses() throws AddressException,EmployeeException;
	
	public Address updateAddress(Integer addressId ,Address address) throws AddressException,EmployeeException;
	
	public Address deleteAddress(Integer addressId) throws AddressException,EmployeeException;
}
