package com.interview.interview_app.mapper;

import com.interview.interview_app.dto.JobDTO;
import com.interview.interview_app.entity.Job;
import com.interview.interview_app.entity.User;

public class JobMapper {

    public static Job toEntity(JobDTO dto) {
        Job job = new Job();
        job.setId(dto.getId());
        job.setJobTitle(dto.getJobTitle());
        job.setJobType(dto.getJobType());
        job.setLocations(dto.getLocations());
        job.setPostedDate(dto.getPostedDate());
        job.setStatus(dto.getStatus());
        job.setCount(dto.getCount());

        // if (dto.getInterviewerId() != null) {
        //     job.setInterviewer(new User(dto.getInterviewerId()));
        // }

        return job;
    }

    public static JobDTO toDTO(Job job) {
        JobDTO dto = new JobDTO();
        dto.setId(job.getId());
        dto.setJobTitle(job.getJobTitle());
        dto.setJobType(job.getJobType());
        dto.setLocations(job.getLocations());
        dto.setPostedDate(job.getPostedDate());
        dto.setStatus(job.getStatus());
        dto.setCount(job.getCount());

        if (job.getInterviewer() != null) {
            dto.setInterviewerId(job.getInterviewer().getId());
        }

        return dto;
    }
}
