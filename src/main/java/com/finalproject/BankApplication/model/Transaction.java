package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Transaction extends BaseEntity{

    @Column(name = "recipient_account" )
    private String recipientAccount;

    @Column(name = "recipient_name" )
    private String recipientName;

    @Column(name = "sender_account" )
    private String senderAccount;

    @Column(name = "sender_name" )
    private String senderName;

    private long amount;
    private String reason;

    @Enumerated(EnumType.STRING)
    private Status status;


}
