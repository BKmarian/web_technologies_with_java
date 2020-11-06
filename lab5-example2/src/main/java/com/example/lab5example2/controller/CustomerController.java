package com.example.lab5example2.controller;

import com.example.lab5example2.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class CustomerController {

    @GetMapping("/customerregistration")
    public String showCustomerFrom(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        List<String> jobs = Arrays.asList("Developer", "tester", "BA", "Architect");
        model.addAttribute("jobs", jobs);

        return "customer-registration-form";
    }

    @PostMapping("/customerregistration")
    public String submitCustomerInfo(@Valid Customer customer, BindingResult bindingResult, Model model)    {
        model.addAttribute("customer", customer);
        if(bindingResult.hasErrors())   {
            return "customer-registration-form";
        } else {
            return "submit-customer-info";
        }
    }
}
