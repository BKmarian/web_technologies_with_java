package service;

import aspect.MarkedForLogging;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void firstTest() {
        System.out.println("TEST"); //business logic
    }

    public void secondTest() {
        System.out.println("TEST2"); //business logic
    }

    public String hello(int hour) {
        if(hour < 12)   {
            return "Good morning!";
        }
        return "Good night!";
    }

    @MarkedForLogging
    public void thirdTest() {
        System.out.println("TEST3");
    }

}
