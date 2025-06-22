package com.interview.interview_app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interview {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    private User interviewer;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private LocalDateTime scheduledDate;

    @Enumerated(EnumType.STRING)
    private InterviewStatus interviewStatus;

    @Enumerated(EnumType.STRING)
    private ShortlistStatus shortlistedStatus;

    @Column(unique = true, nullable = false)
    private String token;

    @Column(nullable = false)
    private Boolean tokenUsed = false;

    public enum InterviewStatus {
        NOT_SCHEDULED, SCHEDULED, COMPLETED
    }

    public enum ShortlistStatus {
        PENDING, SHORTLISTED, REJECTED
    }

    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InterviewQuestion> interviewQuestions;

    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InterviewResult> interviewResults;
}
