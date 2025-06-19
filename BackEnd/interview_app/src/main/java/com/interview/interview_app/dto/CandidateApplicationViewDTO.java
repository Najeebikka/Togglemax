package com.interview.interview_app.dto;

import com.interview.interview_app.entity.Interview.InterviewStatus;
import com.interview.interview_app.entity.Interview.ShortlistStatus;
import lombok.Data;

import java.time.LocalDateTime;  
import java.util.UUID;

@Data
public class CandidateApplicationViewDTO {
    private UUID i_id;
    private UUID id;
    private String name;
    private String photo;
    private String email;
    private String jobTitle;
    private LocalDateTime scheduledDate;           
    private ShortlistStatus shortlistStatus;
    private String resume;
    private InterviewStatus interviewStatus;

    public CandidateApplicationViewDTO(
        UUID i_id,
        UUID id,
        String name,
        String photo,
        String email,
        String jobTitle,
        LocalDateTime scheduledDate,    
        ShortlistStatus shortlistStatus,
        String resume,
        InterviewStatus interviewStatus
    ) {
        this.i_id = i_id;
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.email = email;
        this.jobTitle = jobTitle;
        this.scheduledDate = scheduledDate;
        this.shortlistStatus = shortlistStatus;
        this.resume = resume;
        this.interviewStatus = interviewStatus;
    }
}
