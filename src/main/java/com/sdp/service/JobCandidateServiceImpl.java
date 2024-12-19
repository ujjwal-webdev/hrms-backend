package com.sdp.service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.sdp.dto.JobCandidateDto;
import com.sdp.exception.ResourceNotFoundException;
import com.sdp.model.JobCandidate;
import com.sdp.model.OpenJobPosition;
import com.sdp.repository.JobCandidateRepository;
import com.sdp.repository.OpenJobPositionRepository;
import com.sdp.security.TokenGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class JobCandidateServiceImpl implements JobCandidateService {

    @Autowired
    private JobCandidateRepository jobCandidateRepository;

    @Autowired
    private OpenJobPositionRepository openJobPositionRepository;
    
    @Autowired
	private AuthenticationManager authenticationManager;
    
	@Autowired
	private TokenGenerator tokenGenerator;
    
	@Autowired
	private ModelMapper modelMapper;

    @Override
    public JobCandidateDto applyForJob(JobCandidate jobCandidate, Integer jobId) {
        // Find the job position
        OpenJobPosition jobPosition = openJobPositionRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job position not found"));
        
        List<JobCandidate> candidateList = jobCandidateRepository.findByEmail(jobCandidate.getEmail());

        // Ensure there's at least one candidate in the list
        if (candidateList.isEmpty()) {
            throw new ResourceNotFoundException("Candidate not found with email: " + jobCandidate.getEmail());
        }

        // Get the first candidate from the list (assuming a single unique candidate per email)
        JobCandidate existingCandidate = candidateList.get(0);
        
        // Check if the candidate has already applied for the job
        List<JobCandidate> candidates = jobCandidateRepository.findByEmailAndOpenJobPosition(existingCandidate.getEmail(), jobPosition);
        if (!candidates.isEmpty()) {
            throw new IllegalStateException("Candidate has already applied for this job.");
        }
        
        existingCandidate.setOpenJobPosition(jobPosition);
        existingCandidate.setStatus("Pending");
        JobCandidate updatedCandidate = jobCandidateRepository.save(existingCandidate);
        return modelMapper.map(updatedCandidate, JobCandidateDto.class);
    }

    @Override
    public List<JobCandidateDto> getCandidatesByJobId(Integer jobId) {
        // Find the job position
        OpenJobPosition jobPosition = openJobPositionRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job position not found"));

        // Get the list of candidates for the job
        List<JobCandidate> candidates = jobCandidateRepository.findByOpenJobPosition(jobPosition);

        // Convert to DTOs
        return candidates.stream()
                .map(candidate -> modelMapper.map(candidate, JobCandidateDto.class)) // Mapping using ModelMapper
                .collect(Collectors.toList());
    }

    @Override
    public JobCandidateDto updateCandidateStatus(Integer candidateId, String status) {
        // Find the candidate
        JobCandidate candidate = jobCandidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        // Update the status
        candidate.setStatus(status);

        // Save the updated candidate
        JobCandidate updatedCandidate = jobCandidateRepository.save(candidate);
        
        updatedCandidate.getJobCandidateId();
        updatedCandidate.getFirstName();
        updatedCandidate.getLastName();
        updatedCandidate.getEmail();
        updatedCandidate.getStatus();
        updatedCandidate.getOpenJobPosition().getJobId();

        return modelMapper.map(updatedCandidate, JobCandidateDto.class);
    }

    @Override
    public JobCandidateDto getCandidateById(Integer candidateId) {
        // Find the candidate
        JobCandidate candidate = jobCandidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
        
        candidate.getJobCandidateId();
        candidate.getFirstName();
        candidate.getLastName();
        candidate.getEmail();
        candidate.getStatus();
        candidate.getOpenJobPosition().getJobId();
        
        return modelMapper.map(candidate, JobCandidateDto.class);
    }
    

    @Override
    public JobCandidateDto signUpCandidate(JobCandidateDto jobCandidateDto) {
        // Convert DTO to entity
        JobCandidate jobCandidate = modelMapper.map(jobCandidateDto, JobCandidate.class);

        // Save the entity
        JobCandidate savedCandidate = jobCandidateRepository.save(jobCandidate);

        // Convert entity back to DTO
        return modelMapper.map(savedCandidate, JobCandidateDto.class);
    }
    
    @Override
    public JobCandidateDto signInCandidate(String userName, String password) {
        // Find candidate by userName
        JobCandidate candidate = jobCandidateRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + userName));

        // Verify password
        if (!candidate.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid credentials! Please check your username and password.");
        }

        // Map to DTO and return (exclude password for security)
        JobCandidateDto candidateDto = modelMapper.map(candidate, JobCandidateDto.class);
        candidateDto.setPassword(null); // Mask password
        return candidateDto;
    }
}
