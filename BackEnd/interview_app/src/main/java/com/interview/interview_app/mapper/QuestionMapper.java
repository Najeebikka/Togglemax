package com.interview.interview_app.mapper;

import com.interview.interview_app.dto.QuestionDTO;
import com.interview.interview_app.entity.Question;
import com.interview.interview_app.entity.Subject;

public class QuestionMapper {

    public static Question toEntity(QuestionDTO dto, Subject subject) {
        Question question = new Question();
        question.setId(dto.getId());
        question.setQuestion(dto.getQuestion());
        question.setAnswer(dto.getAnswer());
        question.setSubject(subject);
        return question;
    }

    public static QuestionDTO toDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setQuestion(question.getQuestion());
        dto.setAnswer(question.getAnswer());
        dto.setSubjectId(question.getSubject().getId());
        dto.setSubjectName(question.getSubject().getName());
        return dto;
    }
}
