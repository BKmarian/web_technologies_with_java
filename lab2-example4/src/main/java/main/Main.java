package main;

import config.ProjectConfig;
import jdk.jfr.Percentage;
import model.Cat;
import model.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Cat c = context.getBean("cat", Cat.class);
        Person p = context.getBean(Person.class);

        System.out.println(c);
        System.out.println(p);
        System.out.println(p.getCat());

        context.getBean("depA");
        context.getBean("depB");
    }
}
