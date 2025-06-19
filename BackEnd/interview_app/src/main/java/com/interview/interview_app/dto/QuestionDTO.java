package com.interview.interview_app.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private UUID id;
    private String question;
    private String answer;
    private UUID subjectId;
    private String subjectName;
}
