package com.interview.interview_app.service;

import com.interview.interview_app.dto.CandidateDTO;
import com.interview.interview_app.dto.CandidateApplicationViewDTO;
import com.interview.interview_app.entity.Candidate;
import com.interview.interview_app.entity.User;
import com.interview.interview_app.mapper.CandidateMapper;
import com.interview.interview_app.repository.CandidateRepository;
import com.interview.interview_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;

    public CandidateDTO createCandidate(CandidateDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Candidate candidate = CandidateMapper.toEntity(dto, user);
        return CandidateMapper.toDTO(candidateRepository.save(candidate));
    }

    public List<CandidateDTO> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(CandidateMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CandidateDTO getCandidateById(UUID id) {
        return candidateRepository.findById(id)
                .map(CandidateMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    public CandidateDTO updateCandidate(UUID id, CandidateDTO dto) {
    Candidate existing = candidateRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Candidate not found"));
    
    // Update fields (example)
    existing.setPhoto(dto.getPhoto());
    existing.setResume(dto.getResume());
    existing.setUser(userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found")));

    return CandidateMapper.toDTO(candidateRepository.save(existing));
    }

    public void deleteCandidate(UUID id) {
        candidateRepository.deleteById(id);
    }

    public List<CandidateApplicationViewDTO> getApplicationsForInterviewer(UUID interviewerId) {
        return candidateRepository.findCandidateApplicationsByInterviewerId(interviewerId);
    }

}
