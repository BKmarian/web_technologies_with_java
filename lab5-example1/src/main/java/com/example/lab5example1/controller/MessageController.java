package com.example.lab5example1.controller;

import com.example.lab5example1.model.MyMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class MessageController {

    @Resource(name="messageRequestBean")
    private MyMessage requestBean;

    @Resource(name="messageSessionBean")
    private MyMessage sessionBean;

    @Resource(name="messageApplicationBean")
    private MyMessage applicationBean;

    @RequestMapping("/scoperequest")
    public String getRequestMessage(Model model)    {
        model.addAttribute("firstMessage", requestBean.getMessage());
        requestBean.setMessage("Modified message!");
        model.addAttribute("secondMessage", requestBean.getMessage());
        return "scopepage";
    }

    @RequestMapping("/scopesession")
    public String getSessionMessage(Model model)    {
        model.addAttribute("firstMessage", sessionBean.getMessage());
        sessionBean.setMessage("Modified message!");
        model.addAttribute("secondMessage", sessionBean.getMessage());
        return "scopepage";
    }

    @RequestMapping("/scopeapplication")
    public String getApplicationMessage(Model model)    {
        model.addAttribute("firstMessage", applicationBean.getMessage());
        applicationBean.setMessage("Modified message for application!");
        model.addAttribute("secondMessage", applicationBean.getMessage());
        return "scopepage";
    }
}
