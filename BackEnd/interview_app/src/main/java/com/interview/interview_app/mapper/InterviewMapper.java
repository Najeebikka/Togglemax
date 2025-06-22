package com.interview.interview_app.mapper;

import com.interview.interview_app.dto.InterviewDTO;
import com.interview.interview_app.entity.Candidate;
import com.interview.interview_app.entity.Interview;
import com.interview.interview_app.entity.Job;
import com.interview.interview_app.entity.User;

public class InterviewMapper {

    public static Interview toEntity(InterviewDTO dto, Candidate candidate, Job job, User interviewer) {
        Interview interview = new Interview();
        interview.setId(dto.getId());
        interview.setCandidate(candidate);
        interview.setJob(job);
        interview.setInterviewer(interviewer);
        interview.setScheduledDate(dto.getScheduledDate());
        interview.setInterviewStatus(dto.getInterviewStatus());
        interview.setShortlistedStatus(dto.getShortlistedStatus());
        interview.setToken(dto.getToken());
        interview.setTokenUsed(dto.getTokenUsed());
        return interview;
    }

    public static InterviewDTO toDTO(Interview interview) {
        InterviewDTO dto = new InterviewDTO();
        dto.setId(interview.getId());
        dto.setCandidateId(interview.getCandidate().getId());
        dto.setJobId(interview.getJob().getId());
        dto.setInterviewerId(interview.getInterviewer().getId());
        dto.setScheduledDate(interview.getScheduledDate());
        dto.setInterviewStatus(interview.getInterviewStatus());
        dto.setShortlistedStatus(interview.getShortlistedStatus());
        dto.setToken(interview.getToken());
        dto.setTokenUsed(interview.getTokenUsed());
        return dto;
    }
}
