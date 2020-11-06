package com.example.lab5example2.model;

import com.example.lab5example2.validation.MyValidPassword;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.Date;

@Getter
@Setter
public class Customer {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

//    @NotBlank
//    @Size(min = 8, max = 20)
    @MyValidPassword    //(message = "Test message")
    private String password;

    private String gender;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthday;

    @Min(value = 18, message = "You can't use this app if you're a minor")
    @Max(150)
    private int age;

    @NotBlank
    private String job;

    private String[] personalInterests;

    @AssertTrue
    private boolean acceptTerms;

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", personalInterests=" + Arrays.toString(personalInterests) +
                ", acceptTerms=" + acceptTerms +
                '}';
    }
}
