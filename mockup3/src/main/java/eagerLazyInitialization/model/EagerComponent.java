package eagerLazyInitialization.model;

import org.springframework.stereotype.Component;

@Component
public class Person {

    public Person() {
        System.out.println("Instance of the component has been created!");
    }
}
