package com.studentmanagement.controller;

import com.studentmanagement.model.RegisterRequest;
import com.studentmanagement.model.Student;
import com.studentmanagement.model.User;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(originPatterns = {"http://localhost:*", "http://127.0.0.1:*", "https://*.netlify.app"}, allowCredentials = "true")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/me")
    public Map<String, Object> getMe(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        if (authentication != null) {
            response.put("username", authentication.getName());
            String roles = authentication.getAuthorities().stream()
                    .map(a -> a.getAuthority().replace("ROLE_", ""))
                    .collect(Collectors.joining(","));
            response.put("role", roles);
        }
        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            String role = (request.getRole() == null || request.getRole().isBlank())
                    ? "STUDENT"
                    : request.getRole().trim().toUpperCase();

            if ("STUDENT".equals(role) && request.getRollNo() != null) {
                if (studentRepository.existsById(request.getRollNo())) {
                    return ResponseEntity.badRequest().body(Map.of("message", "Roll Number already exists!"));
                }
            }

            User newUser = new User(
                    request.getUsername().trim(),
                    request.getPassword(),
                    role
            );
            User savedUser = userService.addUser(newUser);

            if ("STUDENT".equals(role) && request.getRollNo() != null) {
                Student student = new Student(
                        request.getRollNo(),
                        savedUser.getUsername(),
                        request.getName() != null && !request.getName().isBlank() ? request.getName().trim() : savedUser.getUsername(),
                        request.getDepartment() != null && !request.getDepartment().isBlank() ? request.getDepartment().trim() : "CSE",
                        request.getSemester() != null ? request.getSemester() : 1,
                        0.0, 0.0, 0.0, 0.0, 0.0, 0.0
                );
                studentRepository.save(student);
            }

            Map<String, Object> res = new HashMap<>();
            res.put("id", savedUser.getId());
            res.put("username", savedUser.getUsername());
            res.put("role", savedUser.getRole());
            res.put("message", "Registration successful! You can now log in.");
            return ResponseEntity.ok(res);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }
}
