package com.interview.interview_app.repository;

import com.interview.interview_app.entity.EmailTemplate;
import com.interview.interview_app.entity.EmailTemplate.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, UUID> {
    boolean existsByTemplateName(String templateName);
    List<EmailTemplate> findByCategory(Category category);
    // Optional<EmailTemplate> findByCategory(Category category);
}
