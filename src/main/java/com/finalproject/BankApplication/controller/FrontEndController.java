
package com.finalproject.BankApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("LKMBank")
@Controller
public class FrontEndController {

//
//    @GetMapping("/login")
//    public String showLogin(){
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String getLogin(){
//        return "login";
//    }
//
//    @GetMapping("/{bankId}/account")
//    public String dashboard(@RequestParam("bankId") String bankId){
//        return "maindashboard";
//    }
//
//    @GetMapping("/bank/{tellerId}/account")
//    public String tellerDashboard(@RequestParam("tellerId") String bankId){
//        return "tellerDashboard";
//    }

//    @GetMapping("/bank/teller/account")
//    public String tellDashboard(){
//        return "Assessment";
//    }
//

    @GetMapping("/{bankId}/account")
    public String dashboard(){
        return "dashboardCustomer";
    }

    @GetMapping("/teller/account/data/view")
    public String showData(@RequestParam("bankId") int bankId){
        return "data";
    }

    @PostMapping("/teller/account/data/change")
    public String editDetails(@RequestParam("bankId") int bankId){
        return "dataedit";
    }

    @GetMapping("/{bankId}/account/transfer")
    public String getTransferForm(@RequestParam("bankId") String bankId){
        return "transaction";
    }

    @PostMapping("/{bankId}/account/transfer")
    public String makeTransaction(@RequestParam("bankId") int bankId){
        return "transaction";
    }

    @GetMapping("/{bankId}/account/balance")
    public String getTransaction(@RequestParam("bankId") int bankId){
        return "balance";
    }
//
//    @GetMapping("/loan")
//    public String askLoanExternal(){
//        return "loan";
//    }
//
//    @PostMapping("/loan")
//    public String loanExternal(){
//        return "loan";
//    }
//
//    @PostMapping("/{bankId}/account/loan")
//    public String askLoanInternal(@RequestParam("bankId") String bankId){
//        return "loan";
//    }
//
//    @GetMapping("/{bankId}/account/loan")
//    public String LoanInternal(@RequestParam("bankId") String bankId){
//        return "loan";
//    }
//
//    @GetMapping("/testuser/account/loan")
//    public String getLoanInternal(){
//        return "loggedLoan";
//    }
//
//    @GetMapping("/testuser/account/data/view")
//    public String getdata(){
//        return "data";
//    }
//
//    @PostMapping("/testuser/account/data/change")
//    public String updatedata(){
//        return "editData";
//    }
//
//    @GetMapping("/testuser/account/data/change")
//    public String editdata(){
//        return "editData";
//    }
//
//    @GetMapping("/testuser/account/maketransfer")
//    public String makeTransfer(){
//        return "transfers";
//    }
//
//    @GetMapping("/testuser/account/transactions")
//    public String checkHistory(){
//        return "transactions";
//    }
//
//    @GetMapping("/testuser/account/loandetails")
//    public String checkLoan(){
//        return "loandetails";
//    }
}
