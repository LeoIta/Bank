package com.finalproject.BankApplication.service;

import com.finalproject.BankApplication.model.Credential;
import com.finalproject.BankApplication.model.Role;
import com.finalproject.BankApplication.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class MyCredentialDetailService implements UserDetailsService {

    @Autowired
    private CredentialService credentialService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String bankId){
        Credential credential = credentialService.findCredentialbyId(bankId);
        List<GrantedAuthority> authorities = getCredentialRole(credential.getRoles());
        return buildCredentialForAuthentication(credential,authorities);

    }

    private List<GrantedAuthority> getCredentialRole(Set<Role> credentialRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : credentialRoles){
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new ArrayList<>(roles);
    }

    private UserDetails buildCredentialForAuthentication(Credential credential, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                credential.getBankId(), credential.getPassword(), authorities
        );

    }

}
