package com.interview.interview_app.service;

import com.interview.interview_app.dto.InterviewQuestionDTO;
import com.interview.interview_app.dto.InterviewDetailsDTO;

import com.interview.interview_app.entity.Candidate;
import com.interview.interview_app.entity.Interview;
import com.interview.interview_app.entity.InterviewQuestion;
import com.interview.interview_app.entity.Job;

import com.interview.interview_app.repository.InterviewQuestionRepository;
import com.interview.interview_app.repository.InterviewRepository;

import java.util.stream.Collectors;

import java.util.List;
import java.util.UUID;

public interface InterviewQuestionService {
    void linkSubjectsToInterview(UUID interviewId, List<UUID> subjectIds);
    List<InterviewQuestionDTO> getAll();

    InterviewDetailsDTO getInterviewDetailsDTO(String token);
}
