package com.interview.interview_app.dto;

import com.interview.interview_app.entity.User.Role;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
}
