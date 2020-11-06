package com.example.lab5example2.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<MyValidPassword, String> {
    @Override
    public void initialize(MyValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(password);
        return password.matches(".*[0-9].*") &&
                password.matches("(.*[@,#,%,&,*,!].*)") &&
                password.matches("(.*[A-Z].*)") &&
                password.matches("(.*[a-z].*)");
    }
}
