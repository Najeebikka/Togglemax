package com.interview.interview_app.service;

import com.interview.interview_app.entity.EmailTemplate;
import com.interview.interview_app.repository.EmailTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailTemplateService {

    private final EmailTemplateRepository repository;

    public EmailTemplateService(EmailTemplateRepository repository) {
        this.repository = repository;
    }

    public List<EmailTemplate> getAllTemplates() {
        return repository.findAll();
    }

    public Optional<EmailTemplate> getTemplateById(UUID id) {
        return repository.findById(id);
    }

    public EmailTemplate addTemplate(EmailTemplate template) {
        if (repository.existsByTemplateName(template.getTemplateName())) {
            throw new RuntimeException("Template with this name already exists");
        }
        return repository.save(template);
    }

    public EmailTemplate updateTemplate(UUID id, EmailTemplate updatedTemplate) {
        return repository.findById(id).map(template -> {
            template.setTemplateName(updatedTemplate.getTemplateName());
            template.setCategory(updatedTemplate.getCategory());
            template.setSubject(updatedTemplate.getSubject());
            template.setEmailBody(updatedTemplate.getEmailBody());
            return repository.save(template);
        }).orElseThrow(() -> new RuntimeException("Template not found"));
    }

    public void deleteTemplate(UUID id) {
        repository.deleteById(id);
    }
}
