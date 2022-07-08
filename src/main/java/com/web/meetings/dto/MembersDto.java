package com.web.meetings.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Members Data Transfer Object:
 * used to pass data with multiple attributes in one shot from client to server, to avoid multiple calls to a remote server.
 * Lombok dependency used to automatically generate getters, setters and constructor
 */
@Getter
@Setter
@NoArgsConstructor
public class MembersDto {
	private String memberName;
	private String meetingId;
}
