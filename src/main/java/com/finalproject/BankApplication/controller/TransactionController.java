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
import java.util.*;

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
            transaction.setSenderBalance(currentBalance); // we save the new balance for the sender
            /*if (isCustomer(transaction.getRecipientAccount())){
                Account receiverAccount = new Account();
                receiverAccount = accountService.findByAccountNumber( transaction.getRecipientAccount());
                long receiverBalance = receiverAccount.getBalance();
                receiverBalance += transaction.getAmount();
                accountService.updateAmountWhereAccountNumber();
                transaction.setReceiverBalance(receiverBalance); // we save the new balance for the sender
            }*/
            if ( transaction.getRecipientAccount().equals("12345")) {
                transaction.setRecipientBalance(transaction.getAmount());
            }else{transaction.setRecipientBalance(0);}

            transactionService.saveTransaction(transaction);
            //transactionService.updateCustomerTransaction(1,500000l);
        }else{
            validationMessage += " rejected. You can not transfer more than you have";
        }

        //implementing sender and receiver balance after transfer

        model.addAttribute("validationMessage",validationMessage);

        model.addAttribute("transaction", transaction);
        return "transferstatus";
    }

    @GetMapping("/LKMBank/testuser/account/transactions/sent")
    public String getTransactionsBySender(Model model){

        List<Transaction> transactions = transactionService.getTransactionsBySender("12345");
        String transferMessage = transactions.size()==0? "You have not made any transaction": " here is your list of sent transactions";
        List<Long> balances = new ArrayList<>();
        for (Transaction transaction :transactions){
            balances.add(transaction.getSenderBalance());
        }
        model.addAttribute("balances", balances);

        //display in terminal for test
        System.out.println("Showing all transactions Sent ");
        for (Transaction transaction :transactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreated_at()+" modified_at"+transaction.getModified_at());
            System.out.println(transaction.toString());
        }
        model.addAttribute("transactions", transactions);
        model.addAttribute("transferMessage", transferMessage);
        return "transactions";
    }

    @GetMapping("/LKMBank/testuser/account/transactions/received")
    public String getTransactionsByRecipient(Model model){

        List<Transaction> transactions = transactionService.getTransactionsByRecipient("12345");
        String transferMessage = transactions.size()==0? "You have not received any transactions": " here is your list of all received transactions";
        List<Long> balances = new ArrayList<>();
        for (Transaction transaction :transactions){
            balances.add(transaction.getRecipientBalance());
        }
        model.addAttribute("balances", balances);

        //display in terminal for test
        System.out.println("Showing all transactions received ");
        for (Transaction transaction :transactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreated_at()+" modified_at"+transaction.getModified_at());
            System.out.println(transaction.toString());

        }
        model.addAttribute("transactions", transactions);
        model.addAttribute("transferMessage", transferMessage);
        return "transactions";

    }

    @GetMapping(value = {"/LKMBank/testuser/account/transactions/all", "/LKMBank/testuser/account/transactions/all"})
    public String getAllTransactions(Model model){

        System.out.println("Showing all transactions");
        //List<Transaction> transactions = transactionService.getAllTransactions();
        List<Transaction> allTransactions = transactionService.getTransactionsByRecipient("12345");
        allTransactions.addAll( transactionService.getTransactionsBySender("12345") ) ;

        String transferMessage = allTransactions.size()==0? "You have no transactions": " here is your list of all transactions";
        for (Transaction transaction :allTransactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreated_at()+" modified_at"+transaction.getModified_at());
            System.out.println(transaction.toString());

        }

        Comparator byDate = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }

            public int compare(Transaction c1, Transaction c2) {
                return Long.valueOf(c1.getCreated_at().getTime()).compareTo(c2.getCreated_at().getTime());
            }
        };

        Collections.sort(allTransactions,byDate);

        List<Long> balances = new ArrayList<>();
        for (Transaction transaction :allTransactions){
            balances.add(transaction.getSenderBalance());
        }
        model.addAttribute("balances", balances);

        model.addAttribute("transactions",allTransactions);
        model.addAttribute("transferMessage", transferMessage);
        return "transactions";
    }

}
