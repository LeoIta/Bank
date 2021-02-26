package com.finalproject.BankApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {

        if (request.isUserInRole("ADMIN")) {
            System.out.println(("I am here ADMIN"));
            return "redirect:/admin/tellerDashboard";
        }
        System.out.println(("I am here customer"));
        return "redirect:/customer/customerDashboard";
    }
}