package com.finalproject.BankApplication.reposiitory;

import com.finalproject.BankApplication.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Integer> {


   //public List<Transaction> findTransactionByRecipient_account(String account_number);


}
