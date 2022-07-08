package com.web.meetings.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Meeting model used to transfer data between the view and controller of the Spring MVC 
 * Lombok used
 * @JsonFormat used to specify how to format fields and/or properties for JSON output
 */
@Getter
@Setter
@NoArgsConstructor
public class MeetingModel {
	private String name;
	private String meetingId;
	private String responsiblePerson;
	private String category;
	private String type;
	private List<MemberModel> members;
	private String description;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Date startDate;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Date endDate;
}
