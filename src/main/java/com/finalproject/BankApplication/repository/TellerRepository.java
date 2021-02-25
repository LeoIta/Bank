package com.finalproject.BankApplication.repository;

import com.finalproject.BankApplication.model.Teller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TellerRepository extends JpaRepository<Teller,Integer> {
}
