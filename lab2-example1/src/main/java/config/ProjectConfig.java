package config;

import model.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean("tom")
    //(value = "tom")
    //(name = "tom")
    //@Primary
    public Cat cat1(){
        Cat cat = new Cat();
        cat.setName("Tom");
        return cat;
    }

    @Bean
    public Cat cat2(){
        Cat cat = new Cat();
        cat.setName("Fluffy");
        return cat;
    }

    @Bean
    public String hello(){
        return "hello";
    }
}
