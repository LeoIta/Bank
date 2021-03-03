package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Account;
import com.finalproject.BankApplication.model.Customer;
import com.finalproject.BankApplication.model.Loan;
import com.finalproject.BankApplication.model.Transaction;
import com.finalproject.BankApplication.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/customer")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoanService loanService;

    @GetMapping ("/customerDashboard")
    public String  dashboard(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        model.addAttribute("customer",customer);
        model.addAttribute("userName", "Hello " + customer.getFirstName());
        model.addAttribute("userMessage","Welcome to LKM Bank!");
        int id=customer.getId();
        Account account = accountService.findAccountByCstId(id);
        model.addAttribute("account",account);
        Loan loan = loanService.findLoanByAccountId(id);
        if(loan!=null){
            loan.setReason("this reason");
            model.addAttribute("loan", loan);}
        return "customer/customerDashboard";
    }

    @GetMapping(value = "/transaction")
    public String getTransactionsPages() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        if (customer.getAccount()==null){
            return "redirect:/customerDashboard";
        }
        return "customer/transaction";
    }

    @GetMapping("/maketransfer")
    public String initiateTransfer(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        if (customer.getAccount()==null){
            return "redirect:/customerDashboard";
        }
        int cstId = customer.getId();
        Account senderAccount = accountService.findAccountById(cstId);
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        model.addAttribute("cst",customer);
        model.addAttribute("acc",senderAccount);
        return "customer/transfers";
    }

    @PostMapping("/maketransfer")
    public String validateTransfer(@ModelAttribute Transaction transaction, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        int cstId = customer.getId();
        //int id =2;
        Account account = accountService.findAccountByCstId(cstId);
        transaction.setSenderName(customer.getFirstName() +" "+ customer.getLastName());
        transaction.setSenderAccount(accountService.findAccountById(cstId).getAccountNumber());
        // end of mocking

        String validationMessage =" You transfer of " + transaction.getAmount() + " to " + transaction.getRecipientName() + " has been ";;
        // implement the verification of balance.
        long currentBalance = account.getBalance();
        if ( transaction.getAmount() < currentBalance ){

            validationMessage += " Validated";
            currentBalance -= transaction.getAmount();
            account.setBalance(currentBalance);
            transaction.setSenderBalance(currentBalance); // we save the new balance for the sender
            if (accountService.isClientOfTheBank(transaction.getRecipientAccount())){
                Account receiverAccount = new Account();
                receiverAccount = accountService.findAccountByAccountNumber(transaction.getRecipientAccount());
                long receiverBalance = receiverAccount.getBalance();
                receiverBalance += transaction.getAmount();         // calculating new balance.
                receiverAccount.setBalance(receiverBalance);        //updating receiver balance
                transaction.setRecipientBalance(receiverBalance); // we save the new balance for the sender

            }else{
                transaction.setRecipientBalance(0);
            }

            transactionService.saveTransaction(transaction);
        }else{
            validationMessage += " rejected. You can not transfer more than you have";
        }

        //implementing sender and receiver balance after transfer
        model.addAttribute("validationMessage",validationMessage);

        model.addAttribute("transaction", transaction);
        return "customer/transferstatus";
    }

    @GetMapping("/transactions/sent")
    public String getTransactionsBySender( Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());

        int cstId = customer.getId();
        String accountNumber = accountService.findAccountByCstId(cstId).getAccountNumber();

        //int id =1;
        List<Transaction> transactions = transactionService.getTransactionsBySender(accountService.findAccountByCstId(cstId).getAccountNumber());
        String transferMessage = transactions.size()==0? "You have not made any transaction": " here is your list of sent transactions";
        List<Long> balances = new ArrayList<>();
        for (Transaction transaction :transactions){
            balances.add(transaction.getSenderBalance());
        }
        model.addAttribute("balances", balances);
        model.addAttribute("transactions", transactions);
        model.addAttribute("transferMessage", transferMessage);
        model.addAttribute("accountNumber",accountNumber);
        return "customer/transactions";
    }

    @GetMapping("/transactions/received")
    public String getTransactionsByRecipient(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());

        int cstId = customer.getId();
        String accountNumber = accountService.findAccountByCstId(cstId).getAccountNumber();

        List<Transaction> transactions = transactionService.getTransactionsByRecipient(accountService.findAccountByCstId(cstId).getAccountNumber());
        String transferMessage = transactions.size()==0? "You have not received any transactions": " here is your list of all received transactions";
        List<Long> balances = new ArrayList<>();
        for (Transaction transaction :transactions){
            balances.add(transaction.getRecipientBalance());
        }
        model.addAttribute("balances", balances);
        model.addAttribute("balanceType","received");
        model.addAttribute("transactions", transactions);
        model.addAttribute("transferMessage", transferMessage);
        model.addAttribute("accountNumber",accountNumber);
        return "customer/transactions";

    }

    @GetMapping(value = {"/transactions", "/transactions/all"})
    public String getAllTransactions(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());

        int cstId = customer.getId();
        String accountNumber = accountService.findAccountByCstId(cstId).getAccountNumber();

        List<Transaction> allTransactions = transactionService.getAllTransactions();
        List<Transaction> allTransactionsForCst = new ArrayList<>();
        for(Transaction transaction :allTransactions){
            if ( (transaction.getSenderAccount()==accountNumber) || transaction.getRecipientAccount()==accountNumber){
                allTransactionsForCst.add(transaction);
            }
        }

        String transferMessage = allTransactions.size()==0? "You have no transactions": " here is your list of all transactions";

        model.addAttribute("accountNumber",accountNumber);
        model.addAttribute("transactions",allTransactionsForCst);
        model.addAttribute("transferMessage", transferMessage);
        return "customer/transactions";
    }

}
