package com.web.meetings.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberModel {
	private String memberName;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Date addedAt;
}