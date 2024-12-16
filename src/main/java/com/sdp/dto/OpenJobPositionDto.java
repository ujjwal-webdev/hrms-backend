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

    private Integer jobId; // Optional for creation; mandatory for updates
    private String description;
    private String position;
    private String experienceRequired;

    // To display the candidates for a position, if needed
//    @JsonIgnore
    private List<JobCandidate> jobCandidates;
}
