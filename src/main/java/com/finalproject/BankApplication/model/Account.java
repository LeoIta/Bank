package com.finalproject.BankApplication.model;

import javax.persistence.*;

@Entity
@Table
public class Account extends BaseEntity {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int clientId;


    @Column
    private long balance;


}
