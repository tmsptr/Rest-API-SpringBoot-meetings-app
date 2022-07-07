package com.web.meetings.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.meetings.dto.MeetingDto;
import com.web.meetings.dto.MembersDto;
import com.web.meetings.model.MeetingModel;
import com.web.meetings.model.MemberModel;
import com.web.meetings.service.MeetingService;

@RestController
@CrossOrigin
@RequestMapping("/api/meeting")
public class MeetingController {
	
	@Autowired
	MeetingService meetingService;
	
	@PostMapping
	public ResponseEntity<MeetingModel> createNewMeeting(@RequestBody MeetingDto meetingDto) {
		return ResponseEntity.ok(meetingService.createNewMeeting(meetingDto)).status(HttpStatus.CREATED).build(); 
	}
	
	@GetMapping
	public ResponseEntity<List<MeetingModel>> getAllMeetings(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "description", defaultValue = "") String description,
			@RequestParam(name = "owner", defaultValue = "") String owner,
			@RequestParam(name = "category", defaultValue = "") String category,
			@RequestParam(name = "type", defaultValue = "") String type,
			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss") Date startDate,
			@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss") Date endDate,
			@RequestParam(name = "noOfAttendies", defaultValue = "0") int noOfAttendies) {
		return ResponseEntity.ok(meetingService.getAllMeetings(name, description, owner, category, type, noOfAttendies, startDate, endDate)); 
	}
	
	@DeleteMapping("/{meetingId}/{person}")
	public ResponseEntity<List<MeetingModel>> deleteMeeting(@PathVariable String meetingId, @PathVariable String person) {
		return ResponseEntity.ok(meetingService.deleteMeeting(meetingId, person)); 
	}
	
	@PostMapping("/add-member")
	public ResponseEntity<MemberModel> addMemberInMeeting(@RequestBody MembersDto membersDto) {
		return ResponseEntity.ok(meetingService.addMemberInMeeting(membersDto)); 
	}
	
	@PostMapping("/remove-member")
	public ResponseEntity<MemberModel> removeMemberInMeeting(@RequestBody MembersDto membersDto) {
		return ResponseEntity.ok(meetingService.removeMemberInMeeting(membersDto)); 
	}
}
