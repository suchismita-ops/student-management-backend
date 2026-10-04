package com.studentmanagement.controller;

import com.studentmanagement.model.AcademicUpdateRequest;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import com.studentmanagement.dsa.StudentPerformanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(originPatterns = {"http://localhost:*", "http://127.0.0.1:*", "https://*.netlify.app"}, allowCredentials = "true")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentPerformanceManager performanceManager;

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        Student saved = studentService.addStudent(student);
        performanceManager.addStudent(saved);
        return saved;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{rollNo}")
    public Student getStudentByRollNo(@PathVariable int rollNo) {
        return studentService.getStudentByRollNo(rollNo);
    }

    @GetMapping("/me")
    public Student getMyProfile(Authentication authentication) {
        return studentService.getStudentByUsername(authentication.getName());
    }

    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) {
        return studentService.searchByName(name);
    }

    @DeleteMapping("/{rollNo}")
    public void deleteStudent(@PathVariable int rollNo) {
        studentService.deleteStudent(rollNo);
        performanceManager.removeStudent(rollNo);
    }

    @PutMapping("/{rollNo}")
    public Student updateStudent(@PathVariable int rollNo, @RequestBody Student student) {
        Student updated = studentService.updateStudent(rollNo, student);
        performanceManager.addStudent(updated); // Update in map
        return updated;
    }

    @PatchMapping("/{rollNo}/academic")
    public Student updateAcademicData(@PathVariable int rollNo, @RequestBody AcademicUpdateRequest request) {
        Student updated = studentService.updateAcademicData(rollNo, request);
        performanceManager.addStudent(updated); // Update in map
        return updated;
    }

    @GetMapping("/average")
    public double getClassAverage() {
        return studentService.calculateClassAverage();
    }

    @GetMapping("/performance")
    public List<Student> getStudentsByPerformance() {
        return studentService.getStudentsByPerformance();
    }

    @GetMapping("/dsa/lookup/{rollNo}")
    public Student findStudentUsingHashMap(@PathVariable int rollNo) {
        return performanceManager.findStudentUsingHashMap(rollNo);
    }
}
