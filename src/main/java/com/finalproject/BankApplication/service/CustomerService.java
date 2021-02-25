package com.finalproject.BankApplication.service;

import com.finalproject.BankApplication.model.Customer;

public interface CustomerService {
    public Customer findUserByEmail(String email) ;
    public Customer saveUser(Customer customer);
}
