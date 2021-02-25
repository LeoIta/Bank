package com.finalproject.BankApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/LKMBank/bank")
public class TellerController {

    @GetMapping("/{tellerId}")
        public String tellerDashboard(){
        return "redirect:/LKMBank/bank/{tellerId}/ticket-console";
}

    @GetMapping("/{tellerId}/ticket-console")
    public String tellerConsole(){
        return "dashboardTeller";
    }


}
