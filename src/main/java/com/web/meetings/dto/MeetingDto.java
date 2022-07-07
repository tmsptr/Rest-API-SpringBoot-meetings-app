package com.web.meetings.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MeetingDto {
	private String name;
	private String responsiblePerson;
	private String category;
	private String type;
	private List<MembersDto> members;
	private String description;
	private String startDate;
	private String endDate;
}
