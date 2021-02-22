package com.finalproject.BankApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Account extends BaseEntity{

    private String account_number;

    public Account(Customer customer, long balance) {
        this.customer = customer;
        this.balance = balance;
        this.accountStatus = FormStatus.SUBMITTED;;
    }

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Column()
    private long balance;

    @OneToOne(targetEntity = Loan.class, cascade = CascadeType.ALL)
    private Loan loan;

    @Enumerated(EnumType.STRING)
    private FormStatus accountStatus;

    public boolean isSubmitted() {
        return accountStatus == FormStatus.SUBMITTED;
    }

    @OneToOne
    private Assessment assessment;
}
