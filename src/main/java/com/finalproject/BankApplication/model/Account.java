package com.finalproject.BankApplication.model;
/*
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Account extends TimeEntity{

    @Column(name="account_number")
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Column()
    private long balance;

    @OneToOne(targetEntity = Loan.class, cascade = CascadeType.ALL)
    private Loan loan;

}
*/