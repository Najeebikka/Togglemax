package com.interview.interview_app.repository;

import com.interview.interview_app.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findBySubject_Id(UUID subjectId);
    List<Question> findBySubject_Name(String name);
}
