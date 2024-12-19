package com.sdp.service;

import com.sdp.dto.OpenJobPositionDto;
import com.sdp.exception.ResourceNotFoundException;
import com.sdp.model.JobCandidate;
import com.sdp.model.OpenJobPosition;
import com.sdp.repository.JobCandidateRepository;
import com.sdp.repository.OpenJobPositionRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class OpenJobPositionServiceImpl implements OpenJobPositionService {

    @Autowired
    private OpenJobPositionRepository jobRepository;
    
    @Autowired
    private JobCandidateRepository jobCandidateRepository;
    
	@Autowired
	private ModelMapper modelMapper;

    // Create a new job
    @Override
    public OpenJobPositionDto createJob(OpenJobPositionDto jobDto) {
        // Convert DTO to Entity
        OpenJobPosition job = new OpenJobPosition();
        job.setDescription(jobDto.getDescription());
        job.setPosition(jobDto.getPosition());
        job.setExperienceRequired(jobDto.getExperienceRequired());

        // Save the job entity
        OpenJobPosition savedJob = jobRepository.save(job);

        // Convert Entity to DTO and return
        return mapToDto(savedJob);
    }

    // Get all jobs
    @Override
    public List<OpenJobPositionDto> getAllJobs() {
        List<OpenJobPosition> jobs = jobRepository.findAll();

        // Convert each job entity to DTO
        return jobs.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    //GetMyApplications
    @Override
    public List<OpenJobPositionDto> getJobsByCandidateEmail(String email) {
        // Fetch all job candidates by email
    	log.info("email: "+ email);
    	List<JobCandidate> candidates = jobCandidateRepository.findByEmail(email);

        // Extract unique job positions applied by the candidate
        List<OpenJobPosition> jobs = candidates.stream()
                .map(JobCandidate::getOpenJobPosition)
                .distinct() // Ensure no duplicate job positions
                .collect(Collectors.toList());

        // Convert each job entity to DTO
        return jobs.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    // Get a specific job by ID
    @Override
    public OpenJobPositionDto getJobById(Integer jobId) {
        OpenJobPosition job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job with ID " + jobId + " not found."));

        // Convert Entity to DTO and return
        return mapToDto(job);
    }

    // Update an existing job
    @Override
    public OpenJobPositionDto updateJob(Integer jobId, OpenJobPositionDto jobDto) {
        // Fetch the existing job
        OpenJobPosition existingJob = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job with ID " + jobId + " not found."));

        // Update job details
        existingJob.setDescription(jobDto.getDescription());
        existingJob.setPosition(jobDto.getPosition());
        existingJob.setExperienceRequired(jobDto.getExperienceRequired());

        // Save the updated job
        OpenJobPosition updatedJob = jobRepository.save(existingJob);

        // Convert Entity to DTO and return
        return mapToDto(updatedJob);
    }

    // Delete a job
    @Override
    public void deleteJob(Integer jobId) {
        // Fetch the job to ensure it exists
        OpenJobPosition job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job with ID " + jobId + " not found."));

        // Delete the job
        jobRepository.delete(job);
    }

    // Helper method to convert Entity to DTO
    private OpenJobPositionDto mapToDto(OpenJobPosition job) {
    	
    	OpenJobPosition savedJob = jobRepository.save(job);
    	
    	savedJob.getJobId();
    	savedJob.getDescription();
    	savedJob.getPosition();
        savedJob.getExperienceRequired();
        
        return modelMapper.map(savedJob, OpenJobPositionDto.class);
    }
}
