package com.interview.interview_app.service;

import com.interview.interview_app.dto.JobDTO;
import com.interview.interview_app.entity.Job;
import com.interview.interview_app.entity.User;
import com.interview.interview_app.mapper.JobMapper;
import com.interview.interview_app.repository.JobRepository;
import com.interview.interview_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobDTO createJob(JobDTO dto) {
        Job job = JobMapper.toEntity(dto);

        if (dto.getInterviewerId() != null) {
            User interviewer = userRepository.findById(dto.getInterviewerId())
                    .orElseThrow(() -> new RuntimeException("Interviewer not found"));
            job.setInterviewer(interviewer);
        }

        return JobMapper.toDTO(jobRepository.save(job));
    }

    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(JobMapper::toDTO)
                .collect(Collectors.toList());
    }

    public JobDTO getJobById(UUID id) {
        return jobRepository.findById(id)
                .map(JobMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public JobDTO updateJob(UUID id, JobDTO dto) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setJobTitle(dto.getJobTitle());
        job.setJobType(dto.getJobType());
        job.setPostedDate(dto.getPostedDate());
        job.setStatus(dto.getStatus());
        job.setCount(dto.getCount());
        job.setLocations(dto.getLocations());

        if (dto.getInterviewerId() != null) {
            User interviewer = userRepository.findById(dto.getInterviewerId())
                    .orElseThrow(() -> new RuntimeException("Interviewer not found"));
            job.setInterviewer(interviewer);
        }

        return JobMapper.toDTO(jobRepository.save(job));
    }

    public void deleteJob(UUID id) {
        jobRepository.deleteById(id);
    }
}
