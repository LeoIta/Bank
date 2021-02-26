package com.finalproject.BankApplication.service;

import com.finalproject.BankApplication.repository.TellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TellerService {
    @Autowired
    TellerRepository tellerRepository;
}
