package config;

import model.EagerComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "model")
public class ProjectConfig {

    @Bean
    public EagerComponent eagerComponent(){
        return new EagerComponent();
    }

}
