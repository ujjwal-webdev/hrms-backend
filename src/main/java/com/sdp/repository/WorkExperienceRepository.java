package com.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdp.model.WorkExperience;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer>{
//
//	@Modifying
//	@Query("DELETE FROM Address WHERE addressId = ?1")
//	public void myDeleteMethod(Integer AddressId);

	
	
}
