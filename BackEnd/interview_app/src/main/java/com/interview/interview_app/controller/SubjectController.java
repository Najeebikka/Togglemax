package com.interview.interview_app.controller;

import com.interview.interview_app.dto.SubjectDTO;
import com.interview.interview_app.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public SubjectDTO create(@RequestBody SubjectDTO dto) {
        return subjectService.create(dto);
    }

    @GetMapping
    public List<SubjectDTO> getAll() {
        return subjectService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable UUID id) {
        subjectService.delete(id);
    }
}

