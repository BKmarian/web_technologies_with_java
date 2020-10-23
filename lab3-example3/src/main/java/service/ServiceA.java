package service;

import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {
    @Autowired
    private Student student;

    public Student getStudent() {
        return student;
    }
}
