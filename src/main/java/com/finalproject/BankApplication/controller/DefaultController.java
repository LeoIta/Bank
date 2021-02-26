/*
package com.finalproject.BankApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("AUTHORITY_USER")) {
            return "redirect:/user/userDashboard";
        }
            return "redirect:/teller/tellerDashboard";
    }
}*/
