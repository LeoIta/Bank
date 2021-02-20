package com.finalproject.BankApplication.reposiitory;

import com.finalproject.BankApplication.model.Account;
import com.finalproject.BankApplication.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential,Integer> {
    Credential findCredentialById(String bankId);
}
