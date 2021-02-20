package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Transaction extends BaseEntity{

    private String recipient_account;
    private String recipient_name;
    private String sender_account;
    private String sender_name;

    private long amount;
    private String reason;

    @Enumerated(EnumType.STRING)
    private Status status;

}
