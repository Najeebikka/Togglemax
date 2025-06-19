package com.interview.interview_app.service;

import com.interview.interview_app.dto.QuestionDTO;
import com.interview.interview_app.entity.Question;
import com.interview.interview_app.entity.InterviewQuestion;
import com.interview.interview_app.mapper.QuestionMapper;
import com.interview.interview_app.repository.QuestionRepository;
import com.interview.interview_app.repository.InterviewQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.interview.interview_app.entity.Subject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SubjectService subjectService;

    public QuestionDTO create(QuestionDTO dto) {
        Subject subject = subjectService.getById(dto.getSubjectId());
        Question question = QuestionMapper.toEntity(dto, subject);
        return QuestionMapper.toDTO(questionRepository.save(question));
    }

    public List<QuestionDTO> getAll() {
        return questionRepository.findAll()
                .stream()
                .map(QuestionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<QuestionDTO> getBySubject(UUID subjectId) {
        return questionRepository.findBySubject_Id(subjectId)
                .stream()
                .map(QuestionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<QuestionDTO> getBySubjectName(String subjectName) {
        List<Question> questions = questionRepository.findBySubject_Name(subjectName);
        return questions.stream().map(QuestionMapper::toDTO).toList();
    }

    public void delete(UUID id) {
        questionRepository.deleteById(id);
    }

}
