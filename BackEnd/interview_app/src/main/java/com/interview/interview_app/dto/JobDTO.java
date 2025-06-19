package com.interview.interview_app.dto;

import com.interview.interview_app.entity.Job.Status;
import com.interview.interview_app.entity.Job.JobType;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class JobDTO {
    private UUID id;
    private UUID interviewerId;
    private String jobTitle;
    private JobType jobType;
    private String locations;
    private LocalDate postedDate;
    private Status status;
    private Integer count;
}
