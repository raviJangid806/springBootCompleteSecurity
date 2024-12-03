package com.spring.practiceSpring.controller;

import com.spring.practiceSpring.model.Student;
import com.spring.practiceSpring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String add(){
        return "student_detail";
    }

//    @GetMapping("/login")
//    public String loginPage(){
//        return "login";
//    }

    @GetMapping("/welcome")
    public String welcomePage(){
        return "welcome";
    }
    @PostMapping("/student-data")
    public String submitForm(@ModelAttribute Student student){
        studentService.addStudent(student);
        return "student_detail";
    }
}
