package com.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdp.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer>{

//	@Modifying
//	@Query("DELETE FROM Address WHERE addressId = ?1")
//	public void myDeleteMethod(Integer AddressId);

	
	
}
