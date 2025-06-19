package com.interview.interview_app.dto;

import com.interview.interview_app.entity.User.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotBlank
    private String name;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String password;

    // Optional: if you want clients to pick roles, otherwise default to CANDIDATE
    private Role role = Role.CANDIDATE;
}
