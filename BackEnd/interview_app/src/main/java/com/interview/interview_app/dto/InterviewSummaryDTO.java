package com.interview.interview_app.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class InterviewSummaryDTO {
    private UUID interviewId;
    private String candidateName;
    private String jobTitle;
    private String videoUrl;
    private LocalDateTime uploadedAt;

    private List<SubjectQuestionGroup> subjectQuestionGroups;
    private List<QuestionAnswerDTO> questionsAndAnswers; 

    @Data
    public static class SubjectQuestionGroup {
        private String subjectName;
        private List<QuestionAnswerDTO> questions;
    }

    @Data
    public static class QuestionAnswerDTO {
        private String questionText;
        private String answerText;
    }
}

