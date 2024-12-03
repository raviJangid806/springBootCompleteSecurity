package com.spring.practiceSpring.service;

import com.spring.practiceSpring.model.Student;
import com.spring.practiceSpring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        student.setRole("USER");
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepository.save(student);
    }
}
