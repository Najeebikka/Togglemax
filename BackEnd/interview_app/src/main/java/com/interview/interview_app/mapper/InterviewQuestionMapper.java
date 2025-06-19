package com.interview.interview_app.mapper;

import com.interview.interview_app.dto.InterviewQuestionDTO;
import com.interview.interview_app.entity.Interview;
import com.interview.interview_app.entity.InterviewQuestion;
import com.interview.interview_app.entity.Subject;

public class InterviewQuestionMapper {

    public static InterviewQuestion toEntity(InterviewQuestionDTO dto, Interview interview, Subject subject) {
        InterviewQuestion interviewQuestion = new InterviewQuestion();
        interviewQuestion.setId(dto.getId());
        interviewQuestion.setInterview(interview);
        interviewQuestion.setSubject(subject);
        return interviewQuestion;
    }

    public static InterviewQuestionDTO toDTO(InterviewQuestion entity) {
        InterviewQuestionDTO dto = new InterviewQuestionDTO();
        dto.setId(entity.getId());
        dto.setInterviewId(entity.getInterview().getId());
        dto.setSubjectId(entity.getSubject().getId());
        dto.setSubjectName(entity.getSubject().getName());
        return dto;
    }
}
