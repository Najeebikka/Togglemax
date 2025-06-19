package com.interview.interview_app.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class InterviewQuestionBulkDTO {
    private UUID interviewId;
    private List<UUID> subjectIds;
}
