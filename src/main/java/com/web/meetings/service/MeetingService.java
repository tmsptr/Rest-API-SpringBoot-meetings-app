package com.web.meetings.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.meetings.dto.MeetingDto;
import com.web.meetings.dto.MembersDto;
import com.web.meetings.exception.MeetingException;
import com.web.meetings.model.MeetingModel;
import com.web.meetings.model.MemberModel;

@Service
public class MeetingService {
	
	@Autowired
	ObjectMapper mapper;
	
	private static final String JSON_FILE_PATH = "data.json";

	public MeetingModel createNewMeeting(MeetingDto meetingDto) {
		MeetingModel model = new MeetingModel();
		model.setName(meetingDto.getName());
		model.setCategory(meetingDto.getCategory());
		model.setDescription(meetingDto.getDescription());
		model.setEndDate(stringToDate(meetingDto.getEndDate()));
		model.setStartDate(stringToDate(meetingDto.getStartDate()));
		model.setMeetingId(UUID.randomUUID().toString());
		model.setResponsiblePerson(meetingDto.getResponsiblePerson());
		model.setType(meetingDto.getType());
		 
		List<MemberModel> membersList = new ArrayList<>();
		if(meetingDto.getMembers() != null && !meetingDto.getMembers().isEmpty()) {
			for(MembersDto dto : meetingDto.getMembers()) {
				MemberModel member = new MemberModel();
				member.setAddedAt(new Date());
				member.setMemberName(dto.getMemberName());
				membersList.add(member);
			}
		}
		model.setMembers(membersList);
		
        List<MeetingModel> meetingJson = readJsonFile();
        meetingJson.add(model);
        writeJsonFile(meetingJson);
		
		return model;
	}

	public List<MeetingModel> getAllMeetings(String name, String description,String owner,String category,String type,int noOfAttendies,Date startDate,Date endDate){
        List<MeetingModel> data = readJsonFile();
        Stream<MeetingModel> stream = data.stream();
        
        return stream.filter(e -> 
        		(!name.trim().equals("") && e.getName().toLowerCase().contains(name.toLowerCase())) || 
        		(!description.trim().equals("") && e.getDescription().toLowerCase().contains(description.toLowerCase())) || 
        		(!owner.trim().equals("") && e.getResponsiblePerson().toLowerCase().contains(owner.toLowerCase())) || 
        		(!category.trim().equals("") && e.getCategory().toLowerCase().contains(category.toLowerCase())) || 
        		(!type.trim().equals("") && e.getType().toLowerCase().contains(type.toLowerCase())) || 
        		(noOfAttendies!=0 && e.getMembers().size() >= noOfAttendies) || 
        		(startDate != null && e.getStartDate().after(startDate)) ||
        		(endDate != null && e.getEndDate().before(endDate)) ||
        		(noOfAttendies==0 && name.trim().equals("") && description.trim().equals("") && owner.trim().equals("") && category.trim().equals("") && type.trim().equals("")  && startDate==null  && endDate==null))
        		.collect(Collectors.toList());
	}

	public List<MeetingModel> deleteMeeting(String meetingId, String person){
		List<MeetingModel> meetings = readJsonFile();
		Optional<MeetingModel> findFirst = meetings.stream().filter(each -> each.getMeetingId().equals(meetingId) && each.getResponsiblePerson().equalsIgnoreCase(person))
								.findFirst();
		if(!findFirst.isPresent()) {
			throw new MeetingException("Cannot delete meeting by user ["+person+"]");
		} else {
			findFirst.map(p -> meetings.remove(p));
		}
		writeJsonFile(meetings);
		return meetings;
	}

	public MemberModel addMemberInMeeting(MembersDto membersDto) {
		List<MeetingModel> meetings = readJsonFile();
		Optional<MeetingModel> findFirst = meetings.stream().filter(each -> each.getMeetingId().equals(membersDto.getMeetingId())).findFirst();
		if(findFirst != null) {
			MeetingModel meeting = findFirst.get();
			if(meeting.getResponsiblePerson().equalsIgnoreCase(membersDto.getMemberName())) {
				throw new MeetingException("Reponsible person cannot be added again in same meeting");
			}
			
			MemberModel members = new MemberModel();
			members.setMemberName(membersDto.getMemberName());
			members.setAddedAt(new Date());
			
			Optional<MemberModel> findAny = meeting.getMembers().stream().filter(each -> each.getMemberName().equalsIgnoreCase(membersDto.getMemberName())).findAny();
			if(findAny.isPresent()) {
				throw new MeetingException("Member already exist");
			} else {
				meeting.getMembers().add(members);
				
				writeJsonFile(meetings);
				return members;
			}
		}
		return null;
	}
	
	public MemberModel removeMemberInMeeting(MembersDto membersDto) {
		List<MeetingModel> meetings = readJsonFile();
		
		Optional<MeetingModel> findFirst = meetings.stream().filter(each -> each.getMeetingId().equalsIgnoreCase(membersDto.getMeetingId())).findFirst();
		if(findFirst != null) {
			MeetingModel meeting = findFirst.get();
			if(meeting.getResponsiblePerson().equalsIgnoreCase(membersDto.getMemberName())) {
				throw new MeetingException("Reponsible person cannot be removed in same meeting");
			}
			
			meeting.getMembers().stream().filter(each -> each.getMemberName().equalsIgnoreCase(membersDto.getMemberName())).findFirst()
					.map(p -> meeting.getMembers().remove(p));
				
			writeJsonFile(meetings);
		}
		return null;	
	}
	
	private List<MeetingModel> readJsonFile(){
		TypeReference<List<MeetingModel>> mapType = new TypeReference<List<MeetingModel>>() {};
        try {
			return mapper.readValue(Paths.get(JSON_FILE_PATH).toFile(), mapType);
		} catch (IOException e) {
			e.printStackTrace();
			throw new MeetingException("JSON file not found");
		}
	}
	
	private void writeJsonFile(List<MeetingModel> meetingList){
        try {
        	mapper.writeValue(Paths.get(JSON_FILE_PATH).toFile(), meetingList);
		} catch (IOException e) {
			e.printStackTrace();
			throw new MeetingException("JSON file not found");
		}
	}

	private Date stringToDate(String date) {
		try {
			return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
