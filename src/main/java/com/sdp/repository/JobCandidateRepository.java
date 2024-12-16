package com.sdp.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdp.model.JobCandidate;
import com.sdp.model.OpenJobPosition;

@Repository
public interface JobCandidateRepository extends JpaRepository<JobCandidate, Integer>{

	List<JobCandidate> findByOpenJobPosition(OpenJobPosition jobPosition);

	Optional<JobCandidate> findByUserName(String userName);

	List<JobCandidate> findByEmailAndOpenJobPosition(String email, OpenJobPosition openJobPosition);
	
	List<JobCandidate> findByEmail(String email);

//	JobCandidate save(List<JobCandidate> existingCandidate);

//	public Optional<Employee> findByUserName(String userName);
//	
//	public Employee findByPassword(String password);
	
}
