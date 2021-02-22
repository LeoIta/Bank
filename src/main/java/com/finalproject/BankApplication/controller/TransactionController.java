package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Status;
import com.finalproject.BankApplication.model.Transaction;
import com.finalproject.BankApplication.reposiitory.TransactionRepository;
import com.finalproject.BankApplication.service.TransactionService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;

@Controller
//@RequestMapping("/")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/transaction")
    public String getTransactionsPages(){
        return "transaction";
    }


    @GetMapping("/transfer")
    public String initiateTransfer(Model model){

        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        return "transfer";
    }

    @PostMapping("/transfer")
    public String validateTransfer(@ModelAttribute Transaction transaction, Model model){

        // impliment the verification of balance.


        transaction.setStatus(Status.CONFIRMED);
        model.addAttribute("transaction", transaction);
        return "transferstatus";
    }

    @GetMapping("/transactions/bysender")
    public String getTransactionsBySender(){

        List<Transaction> transactions = transactionService.getTransactionsBySender("6666");
        //display in terminal for test
        System.out.println("Showing all transactions Sent ");

        for (Transaction transaction :transactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreated_at()+" modified_at"+transaction.getModified_at());
            System.out.println(transaction.toString());

        }
        var params = new HashMap<String, Object>();
        params.put("transactions", transactions);
        //return new ModelAndView("transactions",params);

        return "transactions";
    }

    @GetMapping("/transactions/byrecipient")
    public ModelAndView getTransactionsByRecipient(){

        List<Transaction> transactions = transactionService.getTransactionsByRecipient("12345");
        //display in terminal for test
        System.out.println("Showing all transactions received ");

        for (Transaction transaction :transactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreated_at()+" modified_at"+transaction.getModified_at());
            System.out.println(transaction.toString());

        }
        var params = new HashMap<String, Object>();
        params.put("transactions", transactions);
        return new ModelAndView("transactions",params);

    }


    @GetMapping("/transactions")
    public String getAllTransactions(Model model){

        System.out.println("Showing all transactions");
        List<Transaction> transactions = transactionService.getAllTransactions();

        for (Transaction transaction :transactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreated_at()+" modified_at"+transaction.getModified_at());
            System.out.println(transaction.toString());

        }

        model.addAttribute("transactions",transactions);
        return "transactions";
    }

}
