package com.interview.interview_app.mapper;

import com.interview.interview_app.dto.CandidateDTO;
import com.interview.interview_app.entity.Candidate;
import com.interview.interview_app.entity.User;

public class CandidateMapper {

    private static final String DEFAULT_PHOTO_PATH = "/static/default_photo.png";


    public static Candidate toEntity(CandidateDTO dto, User user) {
        Candidate candidate = new Candidate();
        candidate.setId(dto.getId());
        candidate.setPhoto(dto.getPhoto() != null ? dto.getPhoto() : DEFAULT_PHOTO_PATH);
        candidate.setResume(dto.getResume());
        candidate.setUser(user);
        return candidate;
    }

    public static CandidateDTO toDTO(Candidate candidate) {
        CandidateDTO dto = new CandidateDTO();
        dto.setId(candidate.getId());
        dto.setPhoto(candidate.getPhoto());
        dto.setResume(candidate.getResume());
        dto.setUserId(candidate.getUser().getId());
        dto.setName(candidate.getUser().getName());
        return dto;
    }
}
