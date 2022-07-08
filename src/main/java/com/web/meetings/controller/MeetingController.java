package com.web.meetings.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController // Returns the object and object data is directly written into HTTP response as JSON 
@RequestMapping("/api/meeting") // Maps HTTP requests to handler methods of REST controller
public class MeetingController {
	
	@Autowired // Enables injection of the object dependency implicitly 
	MeetingService meetingService;
	
	/**
	 * REST API handles the HTTP POST requests to create new meeting
	 * Default POST handler and will map to all URLs for which specific matches are not available
	 * @RequestBody maps the HttpRequest body to a transfer object
	 */
	@PostMapping
	public ResponseEntity<MeetingModel> createNewMeeting(@RequestBody MeetingDto meetingDto) {
		return ResponseEntity.ok(meetingService.createNewMeeting(meetingDto)).status(HttpStatus.CREATED).build(); 
	}
	
	/**
	 * REST API handles the HTTP GET requests to load all meetings
	 * Default GET handler and will map to all URLs for which specific matches are not available
	 * @DateTimeFormat Strings properly converted to date objects
	 */
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
	
	/**
	 * REST API handles the HTTP DELETE requests to delete the meeting.
	 * Maps the HTTP DELETE requests onto specific handler method of a Spring controller
	 * @PathVariable indicates that a method parameter should be bound to a URI template variable
	 */
	@DeleteMapping("/{meetingId}/{person}")
	public ResponseEntity<List<MeetingModel>> deleteMeeting(@PathVariable String meetingId, @PathVariable String person) {
		return ResponseEntity.ok(meetingService.deleteMeeting(meetingId, person)); 
	}
	
	/**
	 * REST API handles the HTTP POST requests to add member to the meeting
	 * Maps the HTTP POST requests onto specific handler method of a Spring controller
	 * @RequestBody maps the HttpRequest body to a transfer object
	 */
	@PostMapping("/add-member")
	public ResponseEntity<MemberModel> addMemberInMeeting(@RequestBody MembersDto membersDto) {
		return ResponseEntity.ok(meetingService.addMemberInMeeting(membersDto)); 
	}
	
	/**
	 * REST API handles the HTTP POST requests to remove member in meeting.
	 * Maps the HTTP requests onto specific handler method of a Spring controller
	 * @RequestBody maps the HttpRequest body to a transfer object
	 */
	@PostMapping("/remove-member")
	public ResponseEntity<MemberModel> removeMemberInMeeting(@RequestBody MembersDto membersDto) {
		return ResponseEntity.ok(meetingService.removeMemberInMeeting(membersDto)); 
	}
}
