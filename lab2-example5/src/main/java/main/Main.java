package main;

import config.ProjectConfig;
import models.StudentDetailsPrinter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        StudentDetailsPrinter printer = context.getBean(StudentDetailsPrinter.class);
        printer.printDetails();
    }
}
