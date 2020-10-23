package model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class LazyComponent {
    public LazyComponent() {
        System.out.println("LazyComponent was created!");
    }
}
