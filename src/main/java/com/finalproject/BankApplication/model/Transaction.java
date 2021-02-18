package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Transaction extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    private long amount;
    private String reason;
}
