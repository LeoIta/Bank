package com.finalproject.BankApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("LKMBank")
@Controller
public class MainClientController {

    @GetMapping()
    public String home(){
    return "home";
    }

    @GetMapping("/openAccount")
    public String setAccount(){
        return "openAccount";
    }

    @PostMapping("/openAccount")
    public String getAccount(){
        return "openAccount";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/{bankId}/account")
    public String dashboard(@RequestParam("bankId") String bankId){
        return "mainDashboard";
    }

    @GetMapping("/{bankId}/account/data/view")
    public String showData(@RequestParam("bankId") String bankId){
        return "data";
    }

    @PostMapping("/{bankId}/account/data/change")
    public String editDetails(@RequestParam("bankId") String bankId){
        return "data";
    }

    @GetMapping("/{bankId}/account/transfer")
    public String getTransferForm(@RequestParam("bankId") String bankId){
        return "transaction";
    }

    @PostMapping("/{bankId}/account/transfer")
    public String makeTransaction(@RequestParam("bankId") String bankId){
        return "transaction";
    }

    @GetMapping("/{bankId}/account/balance")
    public String getTransaction(@RequestParam("bankId") String bankId){
        return "balance";
    }

    @GetMapping("/loan/")
    public String askLoanExternal(){
        return "loan";
    }

    @PostMapping("/loan")
    public String loanExternal(){
        return "loan";
    }

    @PostMapping("/{bankId}/account/loan")
    public String askLoanInternal(@RequestParam("bankId") String bankId){
        return "loan";
    }

    @GetMapping("/{bankId}/account/loan")
    public String LoanInternal(@RequestParam("bankId") String bankId){
        return "loan";
    }

}
