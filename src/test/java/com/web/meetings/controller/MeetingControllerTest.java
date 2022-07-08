package com.web.meetings.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.meetings.dto.MeetingDto;
import com.web.meetings.dto.MembersDto;
import com.web.meetings.model.MeetingModel;
import com.web.meetings.model.MemberModel;
import com.web.meetings.service.MeetingService;

/**
 * API tests
 */
@WebMvcTest(controllers = MeetingController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MeetingControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean // Adds mocks to ApplicationContext, mock replaces existing bean
	private MeetingService meetingService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void createMeetingTest() throws Exception {
	    when(meetingService.createNewMeeting(any(MeetingDto.class))).thenReturn(getMeetingModel()); 
	    MvcResult mvcResult = mockMvc.perform(post("/api/meeting")
			  						 .contentType("application/json")
								  	 .content(objectMapper.writeValueAsString(getMeetingDto())))
								  	 .andReturn();
	    String actualResponseBody = mvcResult.getResponse().getContentAsString();
	    assertThat(mvcResult.getResponse().getStatus()).isEqualTo(201);
	}
	
	void getAllMeetingTest() throws Exception {
		List<MeetingModel> list = new ArrayList<>();
		list.add(getMeetingModel());
		  when(meetingService.getAllMeetings(any(String.class),any(String.class),any(String.class),any(String.class),any(String.class),any(Integer.class),any(Date.class),any(Date.class))).thenReturn(list); 
		  MvcResult mvcResult = mockMvc.perform(get("/api/meeting")
				  					   .contentType("application/json")
									   .content(objectMapper.writeValueAsString(getMeetingDto())))
									   .andReturn();
		  String actualResponseBody = mvcResult.getResponse().getContentAsString();
		  System.out.println(actualResponseBody);
		  assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
	  }
	
	void deleteMeetingTest() throws Exception {
		List<MeetingModel> list = new ArrayList<>();
		list.add(getMeetingModel());
		when(meetingService.deleteMeeting(any(String.class),any(String.class))).thenReturn(list); 
		MvcResult mvcResult = mockMvc.perform(delete("/api/meeting/meetingId/person")
				  					 .contentType("application/json")
									 .content(objectMapper.writeValueAsString(getMeetingDto())))
									 .andReturn();
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		System.out.println(actualResponseBody);
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
	  }
	
	void AddMemberMeetingTest() throws Exception {
		List<MeetingModel> list = new ArrayList<>();
		list.add(getMeetingModel());
		when(meetingService.addMemberInMeeting(any(MembersDto.class))).thenReturn(getMemberModel()); 
		MvcResult mvcResult = mockMvc.perform(post("/api/meeting/add-member")
				  					 .contentType("application/json")
									 .content(objectMapper.writeValueAsString(getMembersDto())))
									 .andReturn();
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		System.out.println(actualResponseBody);
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
	  }
	
	@Test
	void removeMemberMeetingTest() throws Exception {
		List<MeetingModel> list = new ArrayList<>();
		list.add(getMeetingModel());
		when(meetingService.removeMemberInMeeting(any(MembersDto.class))).thenReturn(getMemberModel()); 
		MvcResult mvcResult = mockMvc.perform(post("/api/meeting/remove-member")
				  					 .contentType("application/json")
									 .content(objectMapper.writeValueAsString(getMembersDto())))
									 .andReturn();
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		System.out.println(actualResponseBody);
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
	  }
	
	private MeetingModel getMeetingModel() {
		MeetingModel meetingModel = new MeetingModel();
		meetingModel.setName("Test");
		meetingModel.setDescription("Test");
		meetingModel.setMeetingId("Test");
		meetingModel.setResponsiblePerson("Test");
		meetingModel.setType("Test");
		meetingModel.setCategory("Test");
		meetingModel.setStartDate(new Date());
		meetingModel.setEndDate(new Date());
		return meetingModel;
	}
	
	private MeetingDto getMeetingDto() {
		MeetingDto dto = new MeetingDto();
		dto.setName("Test");
		dto.setDescription("Test");
		dto.setResponsiblePerson("Test");
		dto.setType("Test");
		dto.setCategory("Test");
		dto.setStartDate("02-07-2022 12:30:16");
		dto.setEndDate("02-07-2022 12:30:16");
		return dto;
	}
	
	private MemberModel getMemberModel() {
		MemberModel member = new MemberModel();
		member.setMemberName("Test");
		member.setAddedAt(new Date());
		return member;
	}
	
	private MembersDto getMembersDto() {
		MembersDto dto = new MembersDto();
		dto.setMemberName("Test");
		dto.setMeetingId("Test");
		return dto;
	}
}
