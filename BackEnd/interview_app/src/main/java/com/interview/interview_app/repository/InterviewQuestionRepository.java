package com.interview.interview_app.repository;

import com.interview.interview_app.entity.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, UUID> {
    List<InterviewQuestion>findBySubjectId(UUID subjectId);
    List<InterviewQuestion>findByInterview_Id(UUID interviewId);
}
