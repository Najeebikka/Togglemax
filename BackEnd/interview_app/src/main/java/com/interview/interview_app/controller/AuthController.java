// package com.interview.interview_app.controller;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpSession;

// import java.security.Principal;

// @RestController
// @RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
// public class AuthController {

//     // Called after Basic Auth, mainly to confirm session is created
//     @GetMapping("/login")
//     public ResponseEntity<String> login(Principal principal) {
//         if (principal != null) {
//             return ResponseEntity.ok("Welcome, " + principal.getName());
//         } else {
//             return ResponseEntity.status(401).body("Unauthorized");
//         }
//     }

//     // Session-based logout
//     @PostMapping("/logout")
//     public ResponseEntity<String> logout(HttpServletRequest request) {
//         HttpSession session = request.getSession(false); // don't create
//         if (session != null) {
//             session.invalidate();
//         }
//         return ResponseEntity.ok("Logged out successfully");
//     }
// }
