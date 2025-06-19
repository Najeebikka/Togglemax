package com.interview.interview_app.repository;

import com.interview.interview_app.entity.InterviewResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InterviewResultRepository extends JpaRepository<InterviewResult, UUID> {
    List<InterviewResult> findByInterviewId(UUID interviewId);
}