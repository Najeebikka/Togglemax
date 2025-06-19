package com.interview.interview_app.dto;

import com.interview.interview_app.entity.User.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private Role role;
}
