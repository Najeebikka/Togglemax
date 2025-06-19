package com.interview.interview_app.repository;

import com.interview.interview_app.dto.CandidateApplicationViewDTO;
import com.interview.interview_app.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;
import java.util.Optional;


public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

    @Query("""
        SELECT new com.interview.interview_app.dto.CandidateApplicationViewDTO(
            i.id,
            c.id,
            u.name,
            c.photo,
            u.email,
            j.jobTitle,
            i.scheduledDate,
            i.shortlistedStatus,
            c.resume,
            i.interviewStatus
        )
        FROM Interview i
        JOIN i.candidate c
        JOIN c.user u
        JOIN i.job j
        WHERE j.interviewer.id = :interviewerId
    """)
    List<CandidateApplicationViewDTO> findCandidateApplicationsByInterviewerId(UUID interviewerId);
    Optional<Candidate> findByUserId(UUID userId);
}
