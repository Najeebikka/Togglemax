package com.interview.interview_app.controller;

import com.interview.interview_app.dto.LoginRequestDTO;
import com.interview.interview_app.dto.RegisterRequestDTO;
import com.interview.interview_app.dto.RegisterResponseDTO;
import com.interview.interview_app.service.AuthService;
import com.interview.interview_app.service.CustomUserDetailsService;
import com.interview.interview_app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired private AuthService authService;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private CustomUserDetailsService userDetailsService;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(
            @Validated @RequestBody RegisterRequestDTO req
    ) {
        RegisterResponseDTO dto = authService.register(req);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO req) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
        UserDetails ud = userDetailsService.loadUserByUsername(req.getEmail());
        String token = jwtUtil.generateToken(ud);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // JWT is stateless: clients simply discard the token
        return ResponseEntity.ok(Map.of("message", "Logged out"));
    }
}
