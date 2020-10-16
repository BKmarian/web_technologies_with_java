package models;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class SorterByName implements SorterCriteria{
    public void sortStudents() {
        System.out.println("Sort by name");
    }
}
