package models;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("grade")
public class SorterByGrade implements SorterCriteria {
    public void sortStudents() {
        System.out.println("Sort by grade");
    }
}
