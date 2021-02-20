package com.finalproject.BankApplication.service;

import com.finalproject.BankApplication.model.Account;
import com.finalproject.BankApplication.model.Credential;
import com.finalproject.BankApplication.reposiitory.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CredentialService {

    private CredentialRepository credentialRepository;

    @Autowired
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;

    }

    public Credential findByBankId(String bankId){
        return credentialRepository.findByBankId(bankId);
    }

    public Credential saveCredential(Credential credential) {
        credential.setPassword(credential.getPassword());
        return credentialRepository.save(credential);

    }
}
