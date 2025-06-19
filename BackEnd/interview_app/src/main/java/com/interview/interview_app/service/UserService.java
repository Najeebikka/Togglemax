package com.interview.interview_app.service;

import com.interview.interview_app.dto.UserRequestDTO;
import com.interview.interview_app.dto.UserResponseDTO;
import com.interview.interview_app.entity.Candidate;
import com.interview.interview_app.entity.User;
import com.interview.interview_app.mapper.UserMapper;
import com.interview.interview_app.repository.CandidateRepository;
import com.interview.interview_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    private static final String DEFAULT_PHOTO_PATH = "/static/default_photo.png";

    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = UserMapper.toEntity(dto);

        // Set default role if none is provided
        if (user.getRole() == null) {
            user.setRole(User.Role.CANDIDATE);
        }

        // Save the user
        User savedUser = userRepository.save(user);

        // Automatically create candidate if the role is CANDIDATE
        if (savedUser.getRole() == User.Role.CANDIDATE) {
            Candidate candidate = new Candidate();
            candidate.setUser(savedUser);
            candidate.setPhoto(DEFAULT_PHOTO_PATH); // Set default photo
            candidate.setResume(null); // or "" if you prefer an empty string

            candidateRepository.save(candidate);
        }

        return UserMapper.toDTO(savedUser);
    }

    public UserResponseDTO getUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());

        return UserMapper.toDTO(userRepository.save(user));
    }

    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // If the user is a candidate, delete the associated candidate record
        if (user.getRole() == User.Role.CANDIDATE) {
            candidateRepository.findByUserId(id).ifPresent(candidateRepository::delete);
        }

        userRepository.deleteById(id);
    }
}
