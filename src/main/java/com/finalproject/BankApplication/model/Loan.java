package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table
public class Loan extends BaseEntity{
    //also Loan_Request should have reason
    private long amount;
    private String reason;

    @OneToOne(targetEntity = Account.class)
    private Account account;

    @Column(name = "start_date" )
    private Date startDate;
    @Column(name = "due_date" )
    private Date dueDate;
    @Column(name = "pay_day" )
    private int payDay;

    @Enumerated(EnumType.STRING)
    private Status status;
}
