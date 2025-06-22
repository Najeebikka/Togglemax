package com.interview.interview_app.controller;

import com.interview.interview_app.entity.Interview;
import com.interview.interview_app.dto.QuestionDTO;
import com.interview.interview_app.service.QuestionService;
import com.interview.interview_app.repository.InterviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public QuestionDTO create(@RequestBody QuestionDTO dto) {
        return questionService.create(dto);
    }

    @GetMapping
    public List<QuestionDTO> getAll() {
        return questionService.getAll();
    }

    @GetMapping("/by-subject/{subjectId}")
    public List<QuestionDTO> getBySubject(@PathVariable UUID subjectId) {
        return questionService.getBySubject(subjectId);
    }

    @GetMapping("/by-subject-name")
    public List<QuestionDTO> getBySubjectName(@RequestParam String subjectName) {
        return questionService.getBySubjectName(subjectName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable UUID id) {
        try {
            questionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found", e);
        }
    }
}

