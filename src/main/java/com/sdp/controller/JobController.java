package com.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdp.dto.OpenJobPositionDto;
import com.sdp.service.OpenJobPositionService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/jobs")
public class JobController {

    @Autowired
    private OpenJobPositionService jobService;

    // Create a new job position API ----- HR
    @PostMapping("/createJob")
    public ResponseEntity<OpenJobPositionDto> createJob(@RequestBody OpenJobPositionDto jobDto)
    {
        OpenJobPositionDto createdJob = jobService.createJob(jobDto);

        return ResponseEntity.ok(createdJob);
    }

    // Get a specific job position by ID API
    @GetMapping("/getJobById/{getJobById}")
    public ResponseEntity<OpenJobPositionDto> getJobById(@PathVariable("getJobById") Integer jobId)
    {
        OpenJobPositionDto job = jobService.getJobById(jobId);

        return ResponseEntity.ok(job);
    }

    // Update an existing job position API -------- HR
    @PutMapping("/updateJob/{updateJob}")
    public ResponseEntity<OpenJobPositionDto> updateJob(@PathVariable("updateJob") Integer jobId, @RequestBody OpenJobPositionDto jobDto)
    {
        OpenJobPositionDto updatedJob = jobService.updateJob(jobId, jobDto);

        return ResponseEntity.ok(updatedJob);
    }

    // Delete a job position API --------- HR
    @DeleteMapping("/deleteJob/{deleteJob}")
    public ResponseEntity<Void> deleteJob(@PathVariable("deleteJob") Integer jobId)
    {
        jobService.deleteJob(jobId);

        return ResponseEntity.noContent().build();
    }
    
    
}
