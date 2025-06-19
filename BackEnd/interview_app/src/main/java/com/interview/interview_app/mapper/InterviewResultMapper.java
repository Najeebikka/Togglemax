package com.interview.interview_app.mapper;

import com.interview.interview_app.dto.InterviewResultDTO;
import com.interview.interview_app.entity.Interview;
import com.interview.interview_app.entity.InterviewQuestion;
import com.interview.interview_app.entity.InterviewResult;

public class InterviewResultMapper {

    public static InterviewResult toEntity(InterviewResultDTO dto, Interview interview) {
        InterviewResult result = new InterviewResult();
        result.setId(dto.getId());
        result.setInterview(interview);
        result.setVideoUrl(dto.getVideoUrl());
        result.setUploadedAt(dto.getUploadedAt());
        return result;
    }

    public static InterviewResultDTO toDTO(InterviewResult result) {
        InterviewResultDTO dto = new InterviewResultDTO();
        dto.setId(result.getId());
        dto.setInterviewId(result.getInterview().getId());
        dto.setVideoUrl(result.getVideoUrl());
        dto.setUploadedAt(result.getUploadedAt());
        return dto;
    }
}

