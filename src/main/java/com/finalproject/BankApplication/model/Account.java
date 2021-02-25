package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table
public class Account extends BaseEntity{

    @Column(name="account_number")
    private String accountNumber;


    @OneToOne(targetEntity = Customer.class)
    private Customer customerID;

    @Column()
    private long balance;

    @OneToOne(targetEntity = Loan.class, cascade = CascadeType.ALL)
    private Loan loan;


}
