package com.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sdp.model.EducationBackground;

public interface EducationRepository extends JpaRepository<EducationBackground, Integer>{

//	@Modifying
//	@Query("DELETE FROM Education WHERE educationBgId = ?1")
//	public void myDeleteMethod(Integer educationBgId);

	
	
}