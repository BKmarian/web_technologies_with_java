package config;

import model.Cat;
import model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "model")
@PropertySource("classpath:/application.properties")
public class ProjectConfig {
//    @Bean
//    public String name(){
//        return "Gigel";
//    }

//
//    @Bean
//    public Cat cat(){
//        Cat c = new Cat();
//        c.setName("Tom");
//        return c;
//    }
//
//    @Bean("leo")
//    public Cat cat2(){
//        Cat c = new Cat();
//        c.setName("Leo");
//        return c;
//    }
//
//    @Bean
//    public Person person(@Qualifier("leo") Cat cat){
//        Person p = new Person();
//        p.setCat(cat);
//        return p;
//    }

//    @Bean
//    public Person person(){
//        Person p = new Person();
//        p.setCat(cat());
//        return p;
//    }

}
