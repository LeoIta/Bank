package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Account;
import com.finalproject.BankApplication.model.Customer;
import com.finalproject.BankApplication.model.Transaction;
import com.finalproject.BankApplication.repository.AccountRepository;
import com.finalproject.BankApplication.repository.TransactionRepository;
import com.finalproject.BankApplication.service.AccountService;
import com.finalproject.BankApplication.service.CustomerService;
import com.finalproject.BankApplication.service.CustomerServiceImpl;
import com.finalproject.BankApplication.service.TransactionService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.sql.SQLOutput;
import java.util.*;

@Controller
@RequestMapping("/LKMBank/user/{userId}")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private AccountService accountService;

    /*@Autowired
    private AccountService accountService;*/



    @GetMapping //(value="/LKMBank/user/{userId}")
    @ResponseBody
    public String  blabla(@PathVariable("userId") int id){
        System.out.println("oh oh "+id);
        return " This is "+id;
    }

    @GetMapping(value = "/transaction")
    public String getTransactionsPages() {
        System.out.println("I am in transaction");
        return "transaction";
    }

    @GetMapping("/maketransfer")
    public String initiateTransfer(@PathVariable("userId") String ids, Model model){
        // Mocking a customer
        /*long customerBalance = 100000;
        String customerName = "John Geller";
        String accountNumber = "32 4787 4781 4737 3728 7392 5353";
        //model.addAttribute("balance",customerBalance);
        //model.addAttribute("customerName",customerName);
        //model.addAttribute("accountNumber",accountNumber);
         */

        //String senderAccount = accountService.findByCustomerId(id).getAccountNumber;
        //String senderAccount = "12345";
        System.out.println("here is the id : "+ ids);
        //int id = Integer.parseInt(ids);
        Account senderAccount = accountService.findAccountById(1);
        Customer customer = customerService.findUserById(1);

        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        model.addAttribute("cst",customer);
        model.addAttribute("acc",senderAccount);
        return "transfers";
    }

    @PostMapping("/maketransfer")
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

    @GetMapping("/transactions/sent")
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
            System.out.println(transaction.getId() + " created_at" + transaction.getCreatedAt()+" modified_at"+transaction.getModifiedAt());
            System.out.println(transaction.toString());
        }
        model.addAttribute("transactions", transactions);
        model.addAttribute("transferMessage", transferMessage);
        return "transactions";
    }

    @GetMapping("/transactions/received")
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
            System.out.println(transaction.getId() + " created_at" + transaction.getCreatedAt()+" modified_at"+transaction.getModifiedAt());
            System.out.println(transaction.toString());

        }
        model.addAttribute("transactions", transactions);
        model.addAttribute("transferMessage", transferMessage);
        return "transactions";

    }

    @GetMapping(value = {"/transactions/all", "/transactions/all"})
    public String getAllTransactions(Model model){

        System.out.println("Showing all transactions");
        //List<Transaction> transactions = transactionService.getAllTransactions();
        List<Transaction> allTransactions = transactionService.getTransactionsByRecipient("12345");
        allTransactions.addAll( transactionService.getTransactionsBySender("12345") ) ;

        String transferMessage = allTransactions.size()==0? "You have no transactions": " here is your list of all transactions";
        for (Transaction transaction :allTransactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreatedAt()+" modified_at"+transaction.getModifiedAt());
            System.out.println(transaction.toString());

        }

        Comparator byDate = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }

            public int compare(Transaction c1, Transaction c2) {
                return Long.valueOf(c1.getCreatedAt().getTime()).compareTo(c2.getCreatedAt().getTime());
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
