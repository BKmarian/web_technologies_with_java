package com.example.lab4ex3.controller;

import com.example.lab4ex3.model.Student;
import com.example.lab4ex3.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class StudentController {

    private final StudentService service;
    @Value("${message.error}")
    private String message;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/students")
    public String getStudentPage(Model model) {
        model.addAttribute("students", service.getStudents());
        return "students";
    }


    @PostMapping("/student")
    public String addStudent(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String username,
                             Model model)   {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUsername(username);
        service.add(student);
        model.addAttribute("student", student);
        return "student";
    }

    @GetMapping("/student")
    public String test(Model model) {
        model.addAttribute("student", new Student());
        return "student";
    }

    @GetMapping("/info/{username}")
    public String getStudent(@PathVariable String username, Model model)   {
        Optional<Student> student = service.getStudentByUsername(username);
        if(student.isPresent()) {
            model.addAttribute("student", student.get());
            return "student-info";
        } else {
            model.addAttribute("message", message);
            return "error-page";
        }
    }
}
