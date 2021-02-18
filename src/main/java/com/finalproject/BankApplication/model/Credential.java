package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="credentials")
public class Credential extends BaseEntity{

    private String bankId;
    private String password;

    @OneToOne(targetEntity = Account.class)
    private Account account;

    @Enumerated
    private Role role;
}
