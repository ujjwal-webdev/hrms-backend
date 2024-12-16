package com.sdp.service;

import com.sdp.dto.AuthenticatedResponseDto;
import com.sdp.dto.JobCandidateDto;
import com.sdp.dto.LoginDto;
import com.sdp.model.JobCandidate;

import java.util.List;

public interface JobCandidateService {

    JobCandidateDto applyForJob(JobCandidate jobCandidate, Integer jobId);

    List<JobCandidateDto> getCandidatesByJobId(Integer jobId);

    JobCandidateDto updateCandidateStatus(Integer candidateId, String status);

    JobCandidateDto getCandidateById(Integer candidateId);
    
    JobCandidateDto signUpCandidate(JobCandidateDto jobCandidateDto);
    
    JobCandidateDto signInCandidate(String userName, String password);
}
