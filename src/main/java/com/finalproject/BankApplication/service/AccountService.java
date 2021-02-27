package com.finalproject.BankApplication.service;

import com.finalproject.BankApplication.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService{

    AccountRepository accountRepository;
}
