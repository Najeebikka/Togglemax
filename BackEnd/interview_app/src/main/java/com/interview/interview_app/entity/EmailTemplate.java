package com.interview.interview_app.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "email_templates")
public class EmailTemplate {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String templateName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String subject;

    // @Lob
    @Column(name = "email_body", nullable = false, columnDefinition = "TEXT")
    private String emailBody;

    public enum Category {
        INTERVIEW_INVITATION,
        REJECTION,
        SHORTLIST_CONFIRMATION,
        OFFER_LETTER,
        GENERAL_COMMUNICATION
    }

    public EmailTemplate() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getEmailBody() { return emailBody; }
    public void setEmailBody(String emailBody) { this.emailBody = emailBody; }
}
