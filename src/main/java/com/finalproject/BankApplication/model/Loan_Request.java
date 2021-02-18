package com.finalproject.BankApplication.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Loan_Request {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int clientId;

    @Column
    private long amount;

    @Column
    private Date stratDate;

    @Column
    private Date dueDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
}
