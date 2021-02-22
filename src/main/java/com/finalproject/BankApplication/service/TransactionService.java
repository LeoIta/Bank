package com.finalproject.BankApplication.service;

import com.finalproject.BankApplication.model.Transaction;
import com.finalproject.BankApplication.reposiitory.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions(){
        return (List<Transaction>) transactionRepository.findAll();
    }


    // optimum method
    /*public List<Transaction> getTransactionsByReceiver(String bankId){
        List<Transaction> transactions = new ArrayList<>();
        System.out.println("Sender");
        transactions = transactionRepository.findByRecipient_Account(bankId);
        return  transactions;
    }*/

    /*public List<Transaction> getTransactionsBySender(String bankId){
        List<Transaction> transactions = new ArrayList<>();
        System.out.println("Sender");
        transactions = transactionRepository.findBySender_account(bankId);
        return  transactions;
    }*/

    public List<Transaction> getTransactionsByAmount(long amount){
        List<Transaction> transactions = new ArrayList<>();
        System.out.println("Sender");
        //transactions = transactionRepository.findByAmount(amount);
        transactionRepository.findByAmount(amount).forEach(transactions::add);
        return  transactions;
    }

    //outcome
    public List<Transaction> getTransactionsBySender(String account_number){
        List<Transaction> transactions = new ArrayList<>();
        System.out.println("Sender");
        transactions = transactionRepository.findBySenderAccount(account_number);
        return  transactions;
    }
    //INCOME
    public List<Transaction> getTransactionsByRecipient(String account_number){
        List<Transaction> transactions = new ArrayList<>();
        transactions =  transactionRepository.findByRecipientAccount(account_number);
        return transactions;
    }


    public List<Transaction> getAllUserTransactions(String account_number){
        List<Transaction> allTransactions = new ArrayList<>();
        System.out.println("Sent and received");
        for (Transaction transaction :  transactionRepository.findAll()){
            if (transaction.getRecipientAccount().equals(account_number) || transaction.getSenderAccount().equals(account_number) ){
                allTransactions.add(transaction);
            }
        }
        return  allTransactions;
    }

    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }








}
