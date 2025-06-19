package com.interview.interview_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewQuestionDTO {
    private UUID id;
    private UUID interviewId;
    private UUID subjectId;
    private String subjectName;
}
