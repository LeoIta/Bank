package com.finalproject.BankApplication.repository;


import com.finalproject.BankApplication.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    public Account findAccountById(long amount);
    public Account findByCustomerId(int cstId);
    public Boolean existsByAccountNumber(String accountNumber);
    public Account findByAccountNumber(String accountNumber);
}
