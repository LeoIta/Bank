package com.finalproject.BankApplication.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Date dateOfBirth;

    @Column
    private String email;

    @Column
    private long annualIncome;

    @Column
    private long balance;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

}

