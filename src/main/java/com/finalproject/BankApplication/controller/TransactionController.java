package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Transaction;
import com.finalproject.BankApplication.reposiitory.TransactionRepository;
import com.finalproject.BankApplication.service.TransactionService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    /*@RequestMapping("/transactions")
    public String getTransactionsPages(){
        return "transactions";
    }*/

    @GetMapping("/transactions")
    public ModelAndView getTransactions(){
        List<Transaction> transactions = transactionService.getAllTransactions();

        for (Transaction transaction :transactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreated_at()+" modified_at"+transaction.getModified_at());
            System.out.println(transaction.toString());

        }

        transactions = transactionService.getTransactionByRecipient("12345");
        for (Transaction transaction :transactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreated_at()+" modified_at"+transaction.getModified_at());
            System.out.println(transaction.toString());

        }

        transactions = transactionService.getTransactionBySender("11111");
        for (Transaction transaction :transactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreated_at()+" modified_at"+transaction.getModified_at());
            System.out.println(transaction.toString());

        }

        //Transaction transaction = new Transaction( )

        var params = new HashMap<String, Object>();
        params.put("transactions", transactions);
        return new ModelAndView("transactions",params);
    }



}
