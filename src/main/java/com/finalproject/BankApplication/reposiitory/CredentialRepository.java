package com.finalproject.BankApplication.reposiitory;

import com.finalproject.BankApplication.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential,Integer> {
}
