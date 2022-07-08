package com.web.meetings.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Member model used to transfer data between the view and controller of the Spring MVC 
 * Lombok used
 * @JsonFormat used to specify how to format fields and/or properties for JSON output
 */
@Getter
@Setter
@NoArgsConstructor
public class MemberModel {
	private String memberName;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Date addedAt;
}