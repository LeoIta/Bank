package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Loan;
import com.finalproject.BankApplication.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/LKMBank/user/{userId}")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/loandetails")
    public String showLoanDetails(@PathVariable ("userId") int id, Model model){
        Loan loan = loanService.findLoanByAccountId(id);
        model.addAttribute("loan",loan);
        return "loandetails";
    }

}
