package com.finalproject.BankApplication.model;

import javax.persistence.*;

@Entity
@Table
public class Income {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
}
