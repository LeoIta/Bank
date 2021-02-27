package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Account;
import com.finalproject.BankApplication.model.Customer;
import com.finalproject.BankApplication.model.Loan;
import com.finalproject.BankApplication.model.Transaction;
import com.finalproject.BankApplication.repository.AccountRepository;
import com.finalproject.BankApplication.repository.TransactionRepository;
import com.finalproject.BankApplication.service.*;
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

    @Autowired
    private LoanService loanService;



    @GetMapping //(value="/{userId}")
    //@ResponseBody
    public String  blabla(@PathVariable(name="userId") int id, Model model){
        //int id=1;
        System.out.println("oh oh "+id);
        //return " This is "+id;

        Account account = accountService.findAccountByCstId(id);
        model.addAttribute("account",account);
        Loan loan = loanService.findLoanByAccountId(id);
        model.addAttribute("loan", loan);
        return "dashboardCustomer";
    }

    @GetMapping(value = "/transaction")
    public String getTransactionsPages() {
        System.out.println("I am in transaction");
        return "transaction";
    }

    @GetMapping("/maketransfer")
    public String initiateTransfer(@PathVariable("userId") int id, Model model){
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
        System.out.println("here is the id : "+ id);
        //int id = 2;
        Account senderAccount = accountService.findAccountById(id);
        Customer customer = customerService.findUserById(id);

        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        model.addAttribute("cst",customer);
        model.addAttribute("acc",senderAccount);
        return "transfers";
    }

    @PostMapping("/maketransfer")
    public String validateTransfer(@ModelAttribute Transaction transaction, Model model,@PathVariable("userId") int id){
        System.out.println("transfer initiated ......................");
        //int id =2;
        Customer customer = customerService.findUserById(id);
        Account account = accountService.findAccountByCstId(id);
        transaction.setSenderName(customer.getFirstName() +" "+ customer.getLastName());
        transaction.setSenderAccount(accountService.findAccountById(id).getAccountNumber());
        // end of mocking

        String validationMessage =" You transfer of " + transaction.getAmount() + " to " + transaction.getRecipientName() + " has been ";;
        // implement the verification of balance.
        long currentBalance = account.getBalance();
        if ( transaction.getAmount() < currentBalance ){
            System.out.println(" transfer accepted !!!!!! ");
            validationMessage += " Validated";
            currentBalance -= transaction.getAmount();
            account.setBalance(currentBalance);
            System.out.println("sender balance updated ");
            transaction.setSenderBalance(currentBalance); // we save the new balance for the sender
            if (accountService.isClientOfTheBank(transaction.getRecipientAccount())){
                System.out.println(" the receiver is our client is our client");
                Account receiverAccount = new Account();
                receiverAccount = accountService.findAccountByAccountNumber(transaction.getRecipientAccount());
                long receiverBalance = receiverAccount.getBalance();
                receiverBalance += transaction.getAmount();         // calculating new balance.
                System.out.println("receiver balance updated ");
                receiverAccount.setBalance(receiverBalance);        //updating receiver balance
                transaction.setRecipientBalance(receiverBalance); // we save the new balance for the sender
                //accountService.saveAccount(receiverAccount);

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
        return "transferstatus";
    }

    @GetMapping("/transactions/sent")
    public String getTransactionsBySender(@PathVariable("userId") int id, Model model){
        //int id =1;
        List<Transaction> transactions = transactionService.getTransactionsBySender(accountService.findAccountByCstId(id).getAccountNumber());
        System.out.println("we found this many transactions sent"+transactions.size());
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
        model.addAttribute("sent");
        model.addAttribute("balanceType","sent");
        return "transactions";
    }

    @GetMapping("/transactions/received")
    public String getTransactionsByRecipient(@PathVariable("userId") int id,Model model){
        //int id =1;
        List<Transaction> transactions = transactionService.getTransactionsByRecipient(accountService.findAccountByCstId(id).getAccountNumber());
        System.out.println("we found this many transactions received"+transactions.size());
        String transferMessage = transactions.size()==0? "You have not received any transactions": " here is your list of all received transactions";
        List<Long> balances = new ArrayList<>();
        for (Transaction transaction :transactions){
            balances.add(transaction.getRecipientBalance());
        }
        model.addAttribute("balances", balances);
        model.addAttribute("balanceType","received");

        //display in terminal for test
        System.out.println("Showing all transactions received ");
        for (Transaction transaction :transactions) {
            System.out.println(transaction.getId() + " created_at" + transaction.getCreatedAt()+" modified_at"+transaction.getModifiedAt());
            System.out.println(transaction.toString());

        }
        model.addAttribute("transactions", transactions);
        model.addAttribute("transferMessage", transferMessage);
        model.addAttribute("received");
        return "transactions";

    }

    @GetMapping(value = {"/transactions", "/transactions/all"})
    public String getAllTransactions(@PathVariable("userId") int id, Model model){
        //int id =1;
        String accountNumber = accountService.findAccountByCstId(id).getAccountNumber();
        System.out.println("Showing all transactions");
        //List<Transaction> transactions = transactionService.getAllTransactions();
        List<Transaction> allTransactions = transactionService.getTransactionsByRecipient(accountNumber);
        allTransactions.addAll( transactionService.getTransactionsBySender(accountNumber) ) ;

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
        model.addAttribute("balanceType","all");

        model.addAttribute("transactions",allTransactions);
        model.addAttribute("transferMessage", transferMessage);
        return "transactions";
    }

}
