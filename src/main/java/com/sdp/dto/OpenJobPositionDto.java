package com.sdp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdp.model.JobCandidate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenJobPositionDto {

    private Integer jobId;
    private String description;
    private String position;
    private String experienceRequired;

    private List<JobCandidate> jobCandidates;
}
