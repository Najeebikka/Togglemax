package com.interview.interview_app.controller;

import com.interview.interview_app.dto.InterviewDTO;
import com.interview.interview_app.entity.Interview;
import com.interview.interview_app.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    @Autowired
    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    // Create Interview
    @PostMapping
    public ResponseEntity<InterviewDTO> createInterview(@RequestBody InterviewDTO interviewDTO) {
        InterviewDTO created = interviewService.createInterview(interviewDTO);
        return ResponseEntity.ok(created);
    }

    // Get all Interviews
    @GetMapping
    public ResponseEntity<List<InterviewDTO>> getAllInterviews() {
        List<InterviewDTO> interviews = interviewService.getAllInterviews();
        return ResponseEntity.ok(interviews);
    }

    // Get Interview by Id
    @GetMapping("/{id}")
    public ResponseEntity<InterviewDTO> getInterviewById(@PathVariable UUID id) {
        InterviewDTO interview = interviewService.getInterviewById(id);
        return ResponseEntity.ok(interview);
    }

    // Update Interview
    @PutMapping("/{id}")
    public ResponseEntity<InterviewDTO> updateInterview(@PathVariable UUID id, @RequestBody InterviewDTO interviewDetails) {
        InterviewDTO updated = interviewService.updateInterview(id, interviewDetails);
        return ResponseEntity.ok(updated);
    }

    // Delete Interview
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterview(@PathVariable UUID id) {
        interviewService.deleteInterview(id);
        return ResponseEntity.noContent().build();
    }
}
