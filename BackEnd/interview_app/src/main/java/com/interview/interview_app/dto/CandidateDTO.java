package com.interview.interview_app.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CandidateDTO {
    private UUID id;
    private UUID userId;
    private String photo;
    private String resume;
    private String name;
}
