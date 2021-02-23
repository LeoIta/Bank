package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Account;
import com.finalproject.BankApplication.model.Transaction;
import com.finalproject.BankApplication.reposiitory.TransactionRepository;
import com.finalproject.BankApplication.service.AccountService;
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

    /*@Autowired
    private AccountService accountService;*/

    @GetMapping(value = "/transaction")
    public String getTransactionsPages(){
        return "transaction";
    }


    @GetMapping("/LKMBank/testuser/account/maketransfers")
    public String initiateTransfer(Model model){
        // Mocking a customer
        long customerBalance = 100000;
        String customerName = "John Geller";
        String accountNumber = "32 4787 4781 4737 3728 7392 5353";
        model.addAttribute("balance",customerBalance);
        model.addAttribute("customerName",customerName);
        model.addAttribute("accountNumber",accountNumber);
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        return "transfers";
    }

    @PostMapping("/LKMBank/testuser/account/maketransfers")
    public String validateTransfer(@ModelAttribute Transaction transaction, Model model){
        // Mocking the customer again.
        long currentBalance = 100000;
        String customerName = "John Geller";
        String accountNumber = "32 4787 4781 4737 3728 7392 5353";
        transaction.setSenderAccount(customerName); transaction.setSenderAccount(accountNumber);
        // end of mocking

        String validationMessage =" You transfer of " + transaction.getAmount() + " to " + transaction.getRecipientName() + " has been ";;
        // implement the verification of balance.

        if ( transaction.getAmount() < currentBalance ){
            validationMessage += " Validated";
            currentBalance -= transaction.getAmount();
            /*if (isCustomer(transaction.getRecipientAccount())){
                long receiverBalance = 100000;
                receiverBalance += transaction.getAmount();
                Account receiverAccount = new Account();
                receiverAccount = accountService.findByAccountNumber( transaction.getRecipientAccount());
                accountService.updateAmountWhereBankId()
            }*/
            transactionService.saveTransaction(transaction);
        }else{
            validationMessage += " rejected. You can not transfer more than you have";
        }

        model.addAttribute("validationMessage",validationMessage);

        model.addAttribute("transaction", transaction);
        return "transferstatus";
    }

    @GetMapping("/LKMBank/testuser/account/transactions/sent")
    public String getTransactionsBySender(Model model){

        List<Transaction> transactions = transactionService.getTransactionsBySender("6666");
        //display in terminal for test
        System.out.println("Showing all transactions Sent ");
        for (Transaction transaction :transactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreated_at()+" modified_at"+transaction.getModified_at());
            System.out.println(transaction.toString());
        }
        model.addAttribute("transactions", transactions);

        return "transactions";
    }

    @GetMapping("/LKMBank/testuser/account/transactions/received")
    public String getTransactionsByRecipient(Model model){

        List<Transaction> transactions = transactionService.getTransactionsByRecipient("12345");
        //display in terminal for test
        System.out.println("Showing all transactions received ");
        for (Transaction transaction :transactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreated_at()+" modified_at"+transaction.getModified_at());
            System.out.println(transaction.toString());

        }
        model.addAttribute("transactions", transactions);
        return "transactions";

    }

    @GetMapping(value = {"/LKMBank/testuser/account/transactions/all", "/LKMBank/testuser/account/transactions/all"})
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
