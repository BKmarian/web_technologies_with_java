package main;

import config.ProjectConfig;
import model.Cat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        context.registerBean("cat", Cat.class);

        Cat cat = context.getBean("cat", Cat.class);
        System.out.println(cat);

        context.registerBean("cat1", Cat.class, () -> new Cat("Lola"));
        Cat cat1 = context.getBean("cat1", Cat.class);
        System.out.println(cat1);
    }
}
