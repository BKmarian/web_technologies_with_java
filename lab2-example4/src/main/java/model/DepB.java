package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepB {
    private final DepA depA;

    public DepB(DepA depA) {
        this.depA = depA;
    }
}
