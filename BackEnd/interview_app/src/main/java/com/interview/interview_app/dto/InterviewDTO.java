package com.interview.interview_app.dto;

import com.interview.interview_app.entity.Interview.InterviewStatus;
import com.interview.interview_app.entity.Interview.ShortlistStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Data
public class InterviewDTO {
    private UUID id;
    private UUID candidateId;
    private UUID jobId;
    private UUID interviewerId;
    private LocalDateTime scheduledDate;
    private InterviewStatus interviewStatus;
    private ShortlistStatus shortlistedStatus;
    private String token;
    private List<UUID> subjectIds;
}
