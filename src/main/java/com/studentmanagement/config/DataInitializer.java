package com.studentmanagement.config;

import com.studentmanagement.model.Student;
import com.studentmanagement.model.User;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN");
                userRepository.save(admin);
            }
            if (userRepository.findByUsername("faculty").isEmpty()) {
                User faculty = new User("faculty", passwordEncoder.encode("faculty123"), "FACULTY");
                userRepository.save(faculty);
            }
            if (userRepository.findByUsername("student1").isEmpty()) {
                User studentUser = new User("student1", passwordEncoder.encode("student123"), "STUDENT");
                userRepository.save(studentUser);
            }
            if (!studentRepository.existsById(101)) {
                Student sampleStudent = new Student(
                    101,
                    "student1",
                    "Rahul Sharma",
                    "CSE",
                    5,
                    88.0,
                    92.0,
                    85.0,
                    90.0,
                    95.0,
                    92.5
                );
                studentRepository.save(sampleStudent);
            }
        };
    }
}
