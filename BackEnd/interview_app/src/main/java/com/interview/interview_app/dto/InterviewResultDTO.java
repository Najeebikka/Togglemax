package com.interview.interview_app.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class InterviewResultDTO {
    private UUID id;
    private UUID interviewId;
    private String videoUrl;
    private LocalDateTime uploadedAt;
}

