package com.sdp.service;

import com.sdp.dto.OpenJobPositionDto;

import java.util.List;

public interface OpenJobPositionService {

    // Create a new job
    OpenJobPositionDto createJob(OpenJobPositionDto jobDto);

    // Get all jobs
    List<OpenJobPositionDto> getAllJobs();

    // Get a specific job by ID
    OpenJobPositionDto getJobById(Integer jobId);

    // Update an existing job
    OpenJobPositionDto updateJob(Integer jobId, OpenJobPositionDto jobDto);

    // Delete a job
    void deleteJob(Integer jobId);

	List<OpenJobPositionDto> getJobsByCandidateEmail(String email);
}
