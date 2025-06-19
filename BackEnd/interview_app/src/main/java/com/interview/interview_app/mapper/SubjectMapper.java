package com.interview.interview_app.mapper;

import com.interview.interview_app.dto.SubjectDTO;
import com.interview.interview_app.entity.Subject;

public class SubjectMapper {

    public static Subject toEntity(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setId(dto.getId());
        subject.setName(dto.getName());
        return subject;
    }

    public static SubjectDTO toDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        return dto;
    }
}
