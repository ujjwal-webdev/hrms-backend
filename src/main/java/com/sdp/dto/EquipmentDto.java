package com.sdp.dto;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDto {

	public Integer equipmentId;
	private String serialNumber;
	private String name;
	private String model;
	private LocalDate assignedDate;
}
