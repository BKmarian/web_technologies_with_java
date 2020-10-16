package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DepA {
    private final DepB depB;

    public DepA(@Lazy DepB depB) {
        this.depB = depB;
    }
}
