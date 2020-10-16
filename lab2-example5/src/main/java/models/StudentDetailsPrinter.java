package models;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StudentDetailsPrinter {
    private final SorterCriteria sorter;

    public StudentDetailsPrinter(@Qualifier("grade") SorterCriteria sorter) {
        this.sorter = sorter;
    }

    public void printDetails(){
        sorter.sortStudents();
        System.out.println(":)");
    }
}
