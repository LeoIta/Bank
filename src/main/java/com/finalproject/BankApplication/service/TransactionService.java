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

    public List<Transaction> getTransactionBySender(String bankId){
        List<Transaction> transactions = new ArrayList<>();
        System.out.println("Sender");
        for (Transaction transaction :  transactionRepository.findAll()){
            if (transaction.getSender_account().equals(bankId)){
                transactions.add(transaction);
            }
        }
        return  transactions;
    }

    public List<Transaction> getTransactionByRecipient(String account_number){
        List<Transaction> transactions = new ArrayList<>();
        System.out.println("recipient");
        for (Transaction transaction :  transactionRepository.findAll()){
            if (transaction.getRecipient_account().equals(account_number)){
                transactions.add(transaction);
            }
        }
        return  transactions;
        //transactions =  transactionRepository.findTransactionByRecipient_account(account_number);

        //return transactions;
    }


    public List<Transaction> getAllUserTransactions(String account_number){
        List<Transaction> allTransactions = new ArrayList<>();

        System.out.println("Sent and received");
        for (Transaction transaction :  transactionRepository.findAll()){
            if (transaction.getRecipient_account().equals(account_number) || transaction.getSender_account().equals(account_number) ){
                allTransactions.add(transaction);
            }
        }
        return  allTransactions;
    }

    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }








}
