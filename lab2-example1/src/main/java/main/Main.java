package main;

import config.ProjectConfig;
import model.Cat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

//        Cat c = new Cat();
//        c.setName("Jerry");
//        System.out.println(c);  // Nu face parte din context

//        Cat cat = context.getBean(Cat.class);   //by type
//        System.out.println(cat);

        Cat cat = context.getBean("tom", Cat.class);   //by name
        System.out.println(cat);

        Cat cat2 = context.getBean("tom", Cat.class);   //by name
        System.out.println(cat2);

        Cat cat3 = context.getBean("cat2", Cat.class);   //by name
        System.out.println(cat3);

        Cat cat4 = (Cat) context.getBean("cat2");
    }
}
