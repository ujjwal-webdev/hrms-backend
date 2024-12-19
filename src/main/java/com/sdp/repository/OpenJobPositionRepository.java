package com.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdp.model.JobCandidate;
import com.sdp.model.OpenJobPosition;

@Repository
public interface OpenJobPositionRepository extends JpaRepository<OpenJobPosition, Integer>{

}
