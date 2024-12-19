package com.sdp.dto;

import java.time.LocalDate;
import java.util.List;

import com.sdp.Enum.Gender;

import com.sdp.model.JobCandidate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobCandidateDto {

	private String jobCandidateId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private String phoneNo;
    private LocalDate dateOfBirth;
    private String email;
    private String userName;
    private String password;
    private String workCompany1;
    private String workCompany1Skills;
    private String workCompany1Description;
    private String workCompany2;
    private String workCompany2Skills;
    private String workCompany2Description;
    private String education1;
    private String education1Description;
    private String education2;
    private String education2Description;
    private String status;
    private String address;

    private List<OpenJobPositionDto> openJobPosition;
}
