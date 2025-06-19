package com.interview.interview_app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    private User interviewer;

    private String jobTitle;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    private LocalDate postedDate;

    private String locations;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer count;

    public enum JobType {
        FULL_TIME, INTERNSHIP, REMOTE, HYBRID
    }

    public enum Status {
        OPEN, DRAFT, CLOSED
    }

}
