package com.web.meetings.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Meeting Data Transfer Object:
 * used to pass data with multiple attributes in one shot from client to server, to avoid multiple calls to a remote server.
 * Lombok dependency used to automatically generate getters, setters and constructor
 */
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
