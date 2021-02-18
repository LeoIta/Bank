package com.finalproject.BankApplication.model;

import javax.persistence.*;

@Entity
@Table
public class Account {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int client_id;


    @Column
    private long balance;


}
