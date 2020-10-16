package main;

import config.ProjectConfig;
import model.Cat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Cat cat = context.getBean(Cat.class);
        System.out.println(cat);
    }
}
