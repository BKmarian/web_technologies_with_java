package com.example.lab4ex3.service;

import com.example.lab4ex3.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void add(Student student)    {
        students.add(student);
    }

    public Optional<Student> getStudentByUsername(String username)    {
        return students.stream().filter(student -> student.getUsername().compareTo(username) == 0).findAny();
    }
}
