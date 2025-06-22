package com.interview.interview_app.service;

import com.interview.interview_app.dto.InterviewDTO;
import com.interview.interview_app.entity.*;
import com.interview.interview_app.mapper.InterviewMapper;
import com.interview.interview_app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final EmailTemplateRepository emailTemplateRepository;
    private final InterviewResultService interviewResultService;
    private final SubjectRepository subjectRepository;
    private final InterviewQuestionRepository interviewQuestionRepository;

    public InterviewDTO createInterview(InterviewDTO dto) {
        Candidate candidate = candidateRepository.findById(dto.getCandidateId()).orElseThrow();
        Job job = jobRepository.findById(dto.getJobId()).orElseThrow();
        User interviewer = userRepository.findById(dto.getInterviewerId()).orElseThrow();

        Interview interview = InterviewMapper.toEntity(dto, candidate, job, interviewer);
        interview.setToken(UUID.randomUUID().toString());
        interview.setTokenUsed(false);
        Interview saved = interviewRepository.save(interview);

        // ✅ Save subjects into InterviewQuestion
        if (dto.getSubjectIds() != null && !dto.getSubjectIds().isEmpty()) {
            List<Subject> subjects = dto.getSubjectIds().stream()
                    .map(id -> subjectRepository.findById(id).orElseThrow())
                    .toList();

            List<InterviewQuestion> interviewSubjects = subjects.stream()
                    .map(subject -> new InterviewQuestion(null, saved, subject))
                    .toList();

            interviewQuestionRepository.saveAll(interviewSubjects);
        }

        // ✅ Email Sending Logic (if needed)
        List<EmailTemplate> templates = emailTemplateRepository.findByCategory(EmailTemplate.Category.INTERVIEW_INVITATION);
        if (templates.isEmpty()) {
            throw new RuntimeException("No template found");
        }
        EmailTemplate template = templates.stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Email template not found"));


        String token = interview.getToken();

        String tokenUrl = "http://localhost:3000/task/" + token;

        String body = template.getEmailBody()
                .replace("{{candidateName}}", candidate.getUser().getName())
                .replace("{{jobTitle}}", job.getJobTitle())
                .replace("{{scheduledDate}}", dto.getScheduledDate().toString())
                .replace("{{link}}", tokenUrl)
                .replace("{{token}}", token)
                .replace("\n", "<br>");
        
        System.out.println(body);

        // Uncomment the line below to send the email
        emailService.sendEmail(candidate.getUser().getEmail(), template.getSubject(), body);

        return InterviewMapper.toDTO(saved);
    }

    public List<InterviewDTO> getAllInterviews() {
        return interviewRepository.findAll().stream()
                .map(InterviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    public InterviewDTO getInterviewById(UUID id) {
        return interviewRepository.findById(id)
                .map(InterviewMapper::toDTO)
                .orElseThrow();
    }

    public InterviewDTO updateInterview(UUID id, InterviewDTO dto) {
        Interview interview = interviewRepository.findById(id).orElseThrow();
        interview.setScheduledDate(dto.getScheduledDate());
        interview.setInterviewStatus(dto.getInterviewStatus());
        interview.setShortlistedStatus(dto.getShortlistedStatus());
        return InterviewMapper.toDTO(interviewRepository.save(interview));
    }

    public void deleteInterview(UUID id) {
        interviewRepository.deleteById(id);
    }
}
