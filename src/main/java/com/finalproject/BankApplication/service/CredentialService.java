package com.finalproject.BankApplication.service;

import com.finalproject.BankApplication.model.Account;
import com.finalproject.BankApplication.model.Credential;
import com.finalproject.BankApplication.model.Role;
import com.finalproject.BankApplication.reposiitory.CredentialRepository;
import com.finalproject.BankApplication.reposiitory.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class CredentialService {

    private CredentialRepository credentialRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CredentialService(CredentialRepository credentialRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.credentialRepository = credentialRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Credential findCredentialbyId(String bankId) {
        return credentialRepository.findCredentialById(bankId);
    }


    public Credential saveCredential(Credential credential) {
        credential.setPassword(bCryptPasswordEncoder.encode(credential.getPassword()));
        Role credentialRole = roleRepository.findByRole("ADMIN");
        credential.setRoles(new HashSet<Role>(Arrays.asList(credentialRole)));
        return credentialRepository.save(credential);
    }

}