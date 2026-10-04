package com.studentmanagement.service;

import com.studentmanagement.model.AcademicUpdateRequest;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        if (studentRepository.existsById(student.getRollNo())) {
            throw new RuntimeException("Student with Roll No " + student.getRollNo() + " already exists");
        }
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByRollNo(int rollNo) {
        return studentRepository.findById(rollNo).orElse(null);
    }

    public Student getStudentByUsername(String username) {
        return studentRepository.findByUsername(username).orElse(null);
    }

    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteStudent(int rollNo) {
        if (!studentRepository.existsById(rollNo)) {
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(rollNo);
    }

    public Student updateStudent(int rollNo, Student updatedStudent) {
        Student existing = studentRepository.findById(rollNo).orElseThrow(() -> new RuntimeException("Student not found"));
        existing.setUsername(updatedStudent.getUsername());
        existing.setName(updatedStudent.getName());
        existing.setDepartment(updatedStudent.getDepartment());
        existing.setSemester(updatedStudent.getSemester());
        existing.setJavaMarks(updatedStudent.getJavaMarks());
        existing.setDbmsMarks(updatedStudent.getDbmsMarks());
        existing.setDaaMarks(updatedStudent.getDaaMarks());
        existing.setOsMarks(updatedStudent.getOsMarks());
        existing.setWebMarks(updatedStudent.getWebMarks());
        existing.setAttendance(updatedStudent.getAttendance());
        return studentRepository.save(existing);
    }

    public Student updateAcademicData(int rollNo, AcademicUpdateRequest request) {
        Student existing = studentRepository.findById(rollNo).orElseThrow(() -> new RuntimeException("Student not found"));
        existing.setJavaMarks(request.getJavaMarks());
        existing.setDbmsMarks(request.getDbmsMarks());
        existing.setDaaMarks(request.getDaaMarks());
        existing.setOsMarks(request.getOsMarks());
        existing.setWebMarks(request.getWebMarks());
        existing.setAttendance(request.getAttendance());
        return studentRepository.save(existing);
    }

    public double calculateClassAverage() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) return 0.0;
        double sum = 0;
        for (Student s : students) {
            sum += s.calculatePercentage();
        }
        return sum / students.size();
    }

    public List<Student> getStudentsByPerformance() {
        List<Student> students = studentRepository.findAll();
        students.sort((a, b) -> Double.compare(b.calculatePercentage(), a.calculatePercentage()));
        return students;
    }
}
