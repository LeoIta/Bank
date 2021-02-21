package com.finalproject.BankApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("LKMBank/bank")
@Controller
public class MainTellerController {

    @GetMapping("/{TellerId}")
    public String dashboard(@RequestParam("TellerId") String Id){
        return "TellerDashboard";
    }

    @GetMapping("/{TellerId}/accountlist")
    public String showSubmitAccountList(@RequestParam("bankId") String bankId){
        return "accountList";
    }

    @PostMapping("/{TellerId}/accountlist")
    public String assignSubmitAccountList(@RequestParam("bankId") String bankId){
        return "accountList";
    }

    @GetMapping("/{TellerId}/accountlist/mylist")
    public String showTellerAccountList(@RequestParam("bankId") String bankId){
        return "myAccountList";
    }

    @PostMapping("/{TellerId}/accountlist/mylist")
    public String editTellerAccountList(@RequestParam("bankId") String bankId){
        return "myAccountList";
    }

    @GetMapping("/{TellerId}/accountlist/completed")
    public String showCompletedAccountList(@RequestParam("bankId") String bankId){
        return "myList";
    }

    @GetMapping("/{TellerId}/loanlist")
    public String showSubmitLoanList(@RequestParam("bankId") String bankId){
        return "loanList";
    }

    @PostMapping("/{TellerId}/loanlist")
    public String assignSubmitLoanList(@RequestParam("bankId") String bankId){
        return "loanList";
    }

    @GetMapping("/{TellerId}/loanlist/mylist")
    public String showTellerLoanList(@RequestParam("bankId") String bankId){
        return "myLoanList";
    }

    @PostMapping("/{TellerId}/loanlist/mylist")
    public String editTellerLoanList(@RequestParam("bankId") String bankId){
        return "myLoanList";
    }

    @GetMapping("/{TellerId}/loanlist/completed")
    public String showCompletedLoanList(@RequestParam("bankId") String bankId){
        return "myLoanList";
    }

}
