package com.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdp.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer>{

}
