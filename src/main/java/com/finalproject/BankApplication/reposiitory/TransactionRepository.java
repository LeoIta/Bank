package com.finalproject.BankApplication.reposiitory;

import com.finalproject.BankApplication.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Integer> {


   public List<Transaction> findByRecipientAccount(String recipient_account);
   public List<Transaction> findBySenderAccount(String sender_account);
   public List<Transaction> findByAmount(long amount);


}
