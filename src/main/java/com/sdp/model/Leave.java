package com.sdp.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdp.Enum.LeaveStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Leave_Table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Integer leaveId;
	
	private String reason;
	
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	private LeaveStatus status;

	private LocalDate leaveStartDate;
	private LocalDate leaveEndDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Employee employee;

	@Override
	public String toString()
	{
		return "Leave [leaveId=" + leaveId + ", reason=" + reason + ", status=" + status + ", leaveStartDate="
				+ leaveStartDate + ", leaveEndDate=" + leaveEndDate + "]";
	}
}
