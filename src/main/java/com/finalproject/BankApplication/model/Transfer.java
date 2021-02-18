package com.finalproject.BankApplication.model;

import javax.persistence.*;

@Entity
@Table
public class Transfer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int clientId;

    @Column
    private long amount;

    @Column
    private String recipientInfo;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;
}
