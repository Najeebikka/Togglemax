package com.interview.interview_app.service;

import com.interview.interview_app.dto.RegisterRequestDTO;
import com.interview.interview_app.dto.RegisterResponseDTO;
import com.interview.interview_app.entity.User;
import com.interview.interview_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public RegisterResponseDTO register(RegisterRequestDTO req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole());

        User saved = userRepository.save(user);
        return new RegisterResponseDTO(
            saved.getId(),
            saved.getName(),
            saved.getEmail(),
            saved.getRole()
        );
    }
}
