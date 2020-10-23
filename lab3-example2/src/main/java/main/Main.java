package main;

import config.ProjectConfig;
import model.LazyComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var comp = context.getBean(LazyComponent.class);

    }
}
