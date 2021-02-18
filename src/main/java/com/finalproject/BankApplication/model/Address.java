package com.finalproject.BankApplication.model;

import javax.persistence.*;

@Entity
@Table
public class Address {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int clientId;

    @Column
    private int zipCode;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String Country;


}
