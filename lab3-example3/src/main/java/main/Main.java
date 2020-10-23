package main;

import config.ProjectConfig;
import model.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ServiceA;
import service.ServiceB;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

//        Student student1 = context.getBean(Student.class);
//        Student student2 = context.getBean(Student.class);
//
//        System.out.println(student1);
//        System.out.println(student2);

        ServiceA serviceA = context.getBean(ServiceA.class);
        ServiceB serviceB = context.getBean(ServiceB.class);

        boolean result = serviceA.getStudent() == serviceB.getStudent();
        System.out.println(result);
    }
}
