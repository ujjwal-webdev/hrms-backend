package com.sdp.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdp.Enum.WorkStatus;
import com.sdp.Enum.WorkType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkDto {

	public Integer workId;
	private String name;
	private String description;
	private Integer leaderId;
	private LocalDate startDate;
	private LocalDate endDate;

	@Enumerated(EnumType.STRING)
	private WorkType workType;
	
	@Enumerated(EnumType.STRING)
	private WorkStatus status;
}
