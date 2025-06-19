package com.interview.interview_app.service;

import com.interview.interview_app.dto.SubjectDTO;
import com.interview.interview_app.entity.Subject;
import com.interview.interview_app.mapper.SubjectMapper;
import com.interview.interview_app.repository.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectDTO create(SubjectDTO dto) {
        Subject subject = SubjectMapper.toEntity(dto);
        return SubjectMapper.toDTO(subjectRepository.save(subject));
    }

    public List<SubjectDTO> getAll() {
        return subjectRepository.findAll()
                .stream()
                .map(SubjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Subject getById(UUID id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    @Transactional
    public void delete(UUID id) {
        Subject subject = subjectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Subject not found"));
        subjectRepository.delete(subject);
    }

}
