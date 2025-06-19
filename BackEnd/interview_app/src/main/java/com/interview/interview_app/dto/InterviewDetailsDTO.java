package com.interview.interview_app.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class InterviewDetailsDTO {
    private UUID interviewId;
    private String candidateName;
    private String jobTitle;
    private List<String> questionTitles;
}
