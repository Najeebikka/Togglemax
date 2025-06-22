package com.interview.interview_app.controller;

import com.interview.interview_app.dto.InterviewQuestionDTO;
import com.interview.interview_app.dto.InterviewDetailsDTO;
import com.interview.interview_app.dto.InterviewSubjectRequest;
import com.interview.interview_app.service.InterviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview-questions")
@CrossOrigin(origins = "http://localhost")
public class InterviewQuestionController {

    @Autowired
    private InterviewQuestionService interviewQuestionService;

    @PostMapping("/bulk")
    public void bulkLinkSubjects(@RequestBody InterviewSubjectRequest request) {
        interviewQuestionService.linkSubjectsToInterview(request.getInterviewId(), request.getSubjectIds());
    }

    @GetMapping
    public List<InterviewQuestionDTO> getAll() {
        return interviewQuestionService.getAll();
    }

    @GetMapping("/interview-token/{token}")
    public InterviewDetailsDTO getInterviewDetailsDTO(@PathVariable String token) {
        return interviewQuestionService.getInterviewDetailsDTO(token);
    }

}
