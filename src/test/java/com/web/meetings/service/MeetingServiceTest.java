package com.web.meetings.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.meetings.dto.MeetingDto;
import com.web.meetings.dto.MembersDto;
import com.web.meetings.enums.Category;
import com.web.meetings.enums.Type;
import com.web.meetings.exception.MeetingException;
import com.web.meetings.model.MeetingModel;
import com.web.meetings.model.MemberModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingServiceTest {
	
	@Autowired
    private MeetingService meetingService;
	
	@MockBean 
	private ObjectMapper objectMapper;
	
	@Test
  	void createMeeting() throws Exception {
		List<MeetingModel> list = new ArrayList<>();
		list.add(getMeetingModel());
		TypeReference<List<MeetingModel>> mapType = new TypeReference<List<MeetingModel>>() {};
		Mockito.when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(list); 
		MeetingModel meetingModel = meetingService.createNewMeeting(getMeetingDto());
		assertThat(meetingModel.getName()).isEqualTo("Test");
    }
	
	@Test
  	void getAllMeeting() throws Exception {
		List<MeetingModel> list = new ArrayList<>();
		list.add(getMeetingModel());
		TypeReference<List<MeetingModel>> mapType = new TypeReference<List<MeetingModel>>() {};
		Mockito.when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(list); 
		List<MeetingModel> meetingModel = meetingService.getAllMeetings("","","","","",0,new Date(), new Date());
    }
	
	@Test
  	void deleteMeetingUnknownError() throws Exception {
		try {
		List<MeetingModel> list = new ArrayList<>();
		list.add(getMeetingModel());
		TypeReference<List<MeetingModel>> mapType = new TypeReference<List<MeetingModel>>() {};
		Mockito.when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(list); 
		List<MeetingModel> meetingModel = meetingService.deleteMeeting("","test");
		} catch(MeetingException e) {
			assertThat(e.getMessage()).isEqualTo("Cannot delete meeting by user [test]");
		}
    }
	
	@Test
  	void deleteMeetingOwnerError() throws Exception {
		try {
		List<MeetingModel> list = new ArrayList<>();
		list.add(getMeetingModel());
		TypeReference<List<MeetingModel>> mapType = new TypeReference<List<MeetingModel>>() {};
		Mockito.when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(list); 
		List<MeetingModel> meetingModel = meetingService.deleteMeeting("Test","Test");
		} catch(MeetingException e) {
			assertThat(e.getMessage()).isEqualTo("Cannot delete meeting by user [Test]");
		}
    }
	
	@Test
  	void addMemberMeetingOwnerError() throws Exception {
		try {
		List<MeetingModel> list = new ArrayList<>();
		list.add(getMeetingModel());
		TypeReference<List<MeetingModel>> mapType = new TypeReference<List<MeetingModel>>() {};
		Mockito.when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(list); 
		MemberModel meetingModel = meetingService.addMemberInMeeting(getMembersDto());
		} catch(MeetingException e) {
			assertThat(e.getMessage()).isEqualTo("Reponsible person cannot be added again in same meeting");
		}
    }
	
	@Test
  	void addMemberMeetingSameUserError() throws Exception {
		try {
		List<MeetingModel> list = new ArrayList<>();
		list.add(getMeetingModel());
		TypeReference<List<MeetingModel>> mapType = new TypeReference<List<MeetingModel>>() {};
		Mockito.when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(list); 
		meetingService.addMemberInMeeting(getMembersDto());
		} catch(MeetingException e) {
			assertThat(e.getMessage()).isEqualTo("Reponsible person cannot be added again in same meeting");
		}
    }
	@Test
  	void removeMemberMeetingOwnerError() throws Exception {
		try {
		List<MeetingModel> list = new ArrayList<>();
		list.add(getMeetingModel());
		TypeReference<List<MeetingModel>> mapType = new TypeReference<List<MeetingModel>>() {};
		Mockito.when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(list); 
		meetingService.removeMemberInMeeting(getMembersDto());
		} catch(MeetingException e) {
			assertThat(e.getMessage()).isEqualTo("Reponsible person cannot be removed in same meeting");
		}
    }
	
	@Test
  	void removeMemberMeetingUserError() throws Exception {
		try {
			List<MeetingModel> list = new ArrayList<>();
			MeetingModel meetingModel2 = getMeetingModel();
			meetingModel2.setMembers(new ArrayList<>());
			list.add(meetingModel2);
		TypeReference<List<MeetingModel>> mapType = new TypeReference<List<MeetingModel>>() {};
		Mockito.when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(list); 
		meetingService.removeMemberInMeeting(getMembersDto1());
		} catch(MeetingException e) {
			assertThat(e.getMessage()).isEqualTo("Reponsible person cannot be removed in same meeting");
		}
    }
	
	
	@Test
  	void addMemberMeetingNewMemberError() throws Exception {
		List<MeetingModel> list = new ArrayList<>();
		MeetingModel meetingModel2 = getMeetingModel();
		meetingModel2.setMembers(new ArrayList<>());
		list.add(meetingModel2);
		TypeReference<List<MeetingModel>> mapType = new TypeReference<List<MeetingModel>>() {};
		Mockito.when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(list); 
		MemberModel meetingModel = meetingService.addMemberInMeeting(getMembersDto1());
		
    }
	
	private MeetingModel getMeetingModel() {
		MeetingModel meetingModel = new MeetingModel();
		meetingModel.setName("Test");
		meetingModel.setDescription("Test");
		meetingModel.setMeetingId("Test");
		meetingModel.setResponsiblePerson("Test");
		meetingModel.setType(Type.LIVE.toString());
		meetingModel.setCategory(Category.CODE_MONKEY.toString());
		meetingModel.setStartDate(new Date());
		meetingModel.setEndDate(new Date());
		return meetingModel;
	}
	
	private MeetingDto getMeetingDto() {
		MeetingDto dto = new MeetingDto();
		dto.setName("Test");
		dto.setDescription("Test");
		dto.setResponsiblePerson("Test");
		dto.setType(Type.LIVE.toString());
		dto.setCategory(Category.CODE_MONKEY.toString());
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
	
	private MembersDto getMembersDto1() {
		MembersDto dto = new MembersDto();
		dto.setMemberName("Testing user");
		dto.setMeetingId("Test");
		return dto;
	}

}
