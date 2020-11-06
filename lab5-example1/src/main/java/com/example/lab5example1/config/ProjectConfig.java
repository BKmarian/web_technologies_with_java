package com.example.lab5example1.config;

import com.example.lab5example1.model.MyMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class ProjectConfig {

    @Bean
    @RequestScope
   // @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MyMessage messageRequestBean(){
        System.out.println("A Request Scope Bean was created!");
        return new MyMessage("message Request Bean");
    }

    @Bean
    @SessionScope
    public MyMessage messageSessionBean(){
        System.out.println("A Session Scope Bean was created!");
        return new MyMessage("message Session Bean");
    }

    @Bean
    @ApplicationScope
    public MyMessage messageApplicationBean()   {
        System.out.println("A Application Scope Bean was created!");
        return new MyMessage("message Application Bean");
    }
}
