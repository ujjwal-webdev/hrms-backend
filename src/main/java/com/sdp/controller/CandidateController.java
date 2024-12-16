package com.sdp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sdp.dto.CandidateSignInDto;
import com.sdp.dto.JobCandidateDto;
import com.sdp.dto.OpenJobPositionDto;
import com.sdp.model.JobCandidate;
import com.sdp.service.EmployeeService;
import com.sdp.service.JobCandidateService;
import com.sdp.service.JobCandidateServiceImpl;
import com.sdp.service.OpenJobPositionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private JobCandidateService jobCandidateService;
    
    @Autowired
    private OpenJobPositionService jobService;
    
	@Autowired
	private EmployeeService employeeService;

    // Endpoint to apply for a job ------- Candidate
    @PostMapping("/apply/{jobId}")
    public ResponseEntity<JobCandidateDto> applyForJob(
            @PathVariable Integer jobId,
            @Valid @RequestBody JobCandidate jobCandidate) {
        JobCandidateDto jobCandidateDto = jobCandidateService.applyForJob(jobCandidate, jobId);
        return new ResponseEntity<>(jobCandidateDto, HttpStatus.CREATED);
    }

    // Endpoint to get all candidates for a job ---------- HR
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobCandidateDto>> getCandidatesByJobId(@PathVariable Integer jobId) {
        List<JobCandidateDto> candidates = jobCandidateService.getCandidatesByJobId(jobId);
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    // Endpoint to update the status of a candidate ---------- HR
    @PutMapping("/status/{candidateId}")
    public ResponseEntity<JobCandidateDto> updateCandidateStatus(
            @PathVariable Integer candidateId,
            @RequestParam String status) {
        JobCandidateDto updatedCandidate = jobCandidateService.updateCandidateStatus(candidateId, status);
        return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
    }

    // Endpoint to get a candidate by ID -------- HR
    @GetMapping("/getCandidateById/{candidateId}")
    public ResponseEntity<JobCandidateDto> getCandidateById(@PathVariable Integer candidateId) {
        JobCandidateDto candidateDto = jobCandidateService.getCandidateById(candidateId);
        return new ResponseEntity<>(candidateDto, HttpStatus.OK);
    }
    
    @PostMapping("/signup")// -------Candidate
    public ResponseEntity<JobCandidateDto> signUpCandidate(@Valid @RequestBody JobCandidateDto jobCandidateDto) {
        JobCandidateDto savedCandidate = jobCandidateService.signUpCandidate(jobCandidateDto);
        return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
    }
    
    @PostMapping("/signin")// ------Candidate
    public ResponseEntity<JobCandidateDto> signInCandidate(@RequestBody CandidateSignInDto signInRequest) {
        // Extract userName and password from the request body
        String userName = signInRequest.getUserName();
        String password = signInRequest.getPassword();

        // Call the service method
        JobCandidateDto candidate = jobCandidateService.signInCandidate(userName, password);

        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }
    
    @GetMapping("/getMyApplications")// ------- Candidate
    public ResponseEntity<List<OpenJobPositionDto>> getMyApplications(@RequestParam String email) {
        List<OpenJobPositionDto> jobs = jobService.getJobsByCandidateEmail(email);
        
        return ResponseEntity.ok(jobs);
    }
    
    // Get all job positions ------ Candidate
    @GetMapping("/getAllJobs")
    public ResponseEntity<List<OpenJobPositionDto>> getAllJobs() {
        List<OpenJobPositionDto> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }
}
