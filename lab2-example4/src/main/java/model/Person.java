package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {

   // @Autowired
    private Cat cat;
    @Value("${person.name}")
    private String name;

//    @Autowired
//    public Person(Cat cat, String name) {
//        this.cat = cat;
//        this.name = name;
//    }

    public Cat getCat() {
        return cat;
    }

//    public void setCat(Cat cat) {
//        this.cat = cat;
//    }

    @Autowired
    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Person{" +
                "cat=" + cat +
                ", name='" + name + '\'' +
                '}';
    }
}
