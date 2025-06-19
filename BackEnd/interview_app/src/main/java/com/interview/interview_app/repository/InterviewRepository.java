package com.interview.interview_app.repository;

import com.interview.interview_app.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface InterviewRepository extends JpaRepository<Interview, UUID> {
    Optional<Interview> findByToken(String token);
    List<Interview> findByCandidateId(UUID candidateId);
    List<Interview> findByInterviewerId(UUID interviewerId);
}
