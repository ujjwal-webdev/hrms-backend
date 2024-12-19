package com.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sdp.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	@Modifying
	@Query("DELETE FROM Address WHERE addressId = ?1")
	public void myDeleteMethod(Integer AddressId);
}
