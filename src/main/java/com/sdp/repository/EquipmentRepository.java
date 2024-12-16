package com.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdp.model.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer>{

//	@Query("SELECT TIME_TO_SEC(TIMEDIFF(deadLine,current_date()))/86400 FROM Work WHERE workId = ?1")
//	public Integer getDaysDiff(Integer workId); 
	
	
}
