package com.interview.interview_app.service.impl;

import com.interview.interview_app.dto.InterviewQuestionDTO;
import com.interview.interview_app.dto.InterviewDetailsDTO;

import com.interview.interview_app.entity.Interview;
import com.interview.interview_app.entity.InterviewQuestion;
import com.interview.interview_app.entity.Subject;
import com.interview.interview_app.entity.Candidate;
import com.interview.interview_app.entity.Job;
import com.interview.interview_app.entity.Question;

import com.interview.interview_app.mapper.InterviewQuestionMapper;

import com.interview.interview_app.repository.InterviewQuestionRepository;
import com.interview.interview_app.repository.InterviewRepository;
import com.interview.interview_app.repository.SubjectRepository;

import com.interview.interview_app.service.InterviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.stream.Collectors;

@Service
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

    @Autowired
    private InterviewQuestionRepository interviewQuestionRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void linkSubjectsToInterview(UUID interviewId, List<UUID> subjectIds) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        List<Subject> subjects = subjectRepository.findAllById(subjectIds);

        List<InterviewQuestion> interviewQuestions = subjects.stream()
                .map(subject -> {
                    InterviewQuestion iq = new InterviewQuestion();
                    iq.setInterview(interview);
                    iq.setSubject(subject);
                    return iq;
                })
                .collect(Collectors.toList());

        interviewQuestionRepository.saveAll(interviewQuestions);
    }

    @Override
    public List<InterviewQuestionDTO> getAll() {
        return interviewQuestionRepository.findAll()
                .stream()
                .map(InterviewQuestionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InterviewDetailsDTO getInterviewDetailsDTO(String token) {
        Interview interview = interviewRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        Candidate candidate = interview.getCandidate();
        Job job = interview.getJob();

        List<InterviewQuestion> interviewQuestions = interviewQuestionRepository.findByInterview_Id(interview.getId());

        // Use a set to avoid duplicate questions if multiple InterviewQuestion point to the same subject
        List<String> questionTitles = interviewQuestions.stream()
                .flatMap(iq -> iq.getSubject().getQuestions().stream())
                .map(Question::getQuestion)
                // .distinct() // Optional: removes duplicate question texts
                .collect(Collectors.toList());

        InterviewDetailsDTO response = new InterviewDetailsDTO();
        response.setCandidateName(candidate.getUser().getName());
        response.setJobTitle(job.getJobTitle());
        response.setInterviewId(interview.getId());
        response.setQuestionTitles(questionTitles);

        return response;
    }
}
