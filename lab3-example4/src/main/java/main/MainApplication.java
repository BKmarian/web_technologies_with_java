package main;

import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.MyService;

public class MainApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        MyService service = context.getBean(MyService.class);
        service.firstTest();
        service.secondTest();
        String hello = service.hello(11);
        System.out.println(hello);

        service.thirdTest();
    }
}
