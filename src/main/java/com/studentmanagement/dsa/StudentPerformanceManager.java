package com.studentmanagement.dsa;

import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class StudentPerformanceManager {

    private HashMap<Integer, Student> studentMap = new HashMap<>();

    @Autowired
    private StudentRepository studentRepository;

    @PostConstruct
    public void loadStudentsFromDatabase() {
        loadStudents(studentRepository.findAll());
    }

    public void loadStudents(List<Student> students) {
        studentMap.clear();
        for (Student student : students) {
            studentMap.put(student.getRollNo(), student);
        }
    }

    public Student findStudent(int rollNo) {
        return studentMap.get(rollNo);
    }
    
    public Student findStudentUsingHashMap(int rollNo) {
        return studentMap.get(rollNo);
    }

    public void addStudent(Student student) {
        studentMap.put(student.getRollNo(), student);
    }

    public void removeStudent(int rollNo) {
        studentMap.remove(rollNo);
    }

    public boolean containsStudent(int rollNo) {
        return studentMap.containsKey(rollNo);
    }

    public int getStudentCount() {
        return studentMap.size();
    }
}
