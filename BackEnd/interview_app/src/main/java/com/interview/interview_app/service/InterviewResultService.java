package com.interview.interview_app.service;

import com.interview.interview_app.dto.InterviewResultDTO;
import com.interview.interview_app.dto.InterviewSummaryDTO;
import com.interview.interview_app.entity.Interview;
import com.interview.interview_app.entity.InterviewQuestion;
import com.interview.interview_app.entity.InterviewResult;
import com.interview.interview_app.mapper.InterviewResultMapper;
import com.interview.interview_app.repository.InterviewQuestionRepository;
import com.interview.interview_app.repository.QuestionRepository;
import com.interview.interview_app.repository.InterviewRepository;
import com.interview.interview_app.repository.InterviewResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewResultService {
    private final InterviewResultRepository interviewResultRepository;
    private final InterviewQuestionRepository interviewQuestionRepository;
    private final InterviewRepository interviewRepository;
    private final QuestionRepository questionRepository;

    public InterviewResultDTO createResult(InterviewResultDTO dto) {
        Interview interview = interviewRepository.findById(dto.getInterviewId())
                .orElseThrow(() -> new NoSuchElementException("Interview not found with ID: " + dto.getInterviewId()));

        InterviewResult result = InterviewResultMapper.toEntity(dto, interview);
        return InterviewResultMapper.toDTO(interviewResultRepository.save(result));
    }

    public InterviewResultDTO getResultById(UUID id) {
        return interviewResultRepository.findById(id)
                .map(InterviewResultMapper::toDTO)
                .orElseThrow();
    }

    public void deleteResult(UUID id) {
        interviewResultRepository.deleteById(id);
    }

    public List<InterviewSummaryDTO> getAllSummaries() {
        return interviewResultRepository.findAll().stream().map(result -> {
            InterviewSummaryDTO dto = new InterviewSummaryDTO();
            dto.setInterviewId(result.getInterview().getId());
            dto.setCandidateName(result.getInterview().getCandidate().getUser().getName());
            dto.setJobTitle(result.getInterview().getJob().getJobTitle());
            dto.setUploadedAt(result.getUploadedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<InterviewSummaryDTO> getResultsByInterviewId(UUID interviewId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new NoSuchElementException("Interview not found"));

        List<InterviewResult> results = interviewResultRepository.findByInterviewId(interviewId);
        InterviewResult result = results.stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Interview result not found"));

        List<InterviewQuestion> interviewSubjects = interviewQuestionRepository.findByInterview_Id(interviewId);

        List<InterviewSummaryDTO.QuestionAnswerDTO> qaList = interviewSubjects.stream()
                .filter(iq -> {
                    if (iq.getSubject() == null) {
                        System.out.println("Warning: InterviewQuestion with ID " + iq.getId() + " has null subject");
                        return false;
                    }
                    return true;
                })
                .flatMap(iq ->
                        questionRepository.findBySubject_Id(iq.getSubject().getId()).stream()
                                .map(q -> {
                                    InterviewSummaryDTO.QuestionAnswerDTO qa = new InterviewSummaryDTO.QuestionAnswerDTO();
                                    qa.setQuestionText(q.getQuestion());
                                    qa.setAnswerText(q.getAnswer());
                                    return qa;
                                })
                )
                .toList();

        InterviewSummaryDTO summary = new InterviewSummaryDTO();
        summary.setInterviewId(interviewId);
        summary.setCandidateName(interview.getCandidate().getUser().getName());
        summary.setJobTitle(interview.getJob().getJobTitle());
        summary.setUploadedAt(result.getUploadedAt());
        summary.setVideoUrl(result.getVideoUrl());
        summary.setQuestionsAndAnswers(qaList);

        return List.of(summary);
    }
}
