package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Credential extends BaseEntity{

    @Column
    private String bankId;

    @Column
    private String password;

    @OneToOne(targetEntity = Account.class)
    private Account account;

    @Enumerated(EnumType.STRING)
    private Role role;
}
