package com.finalproject.BankApplication.service;

import com.finalproject.BankApplication.model.Account;
import com.finalproject.BankApplication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account findAccountById(int id){

        return accountRepository.findById(id).get();
    }
}

