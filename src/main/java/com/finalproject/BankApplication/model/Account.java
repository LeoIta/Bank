package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="accounts")
public class Account extends BaseEntity{

    private String account_number;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @OneToOne(targetEntity = Credential.class, cascade = CascadeType.ALL)
    private Credential credential;

    @Column()
    private long balance;

    @OneToOne(targetEntity = Loan_Request.class, cascade = CascadeType.ALL)
    private Loan_Request loan;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Transfer> transfers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Income> incomes = new HashSet<>();

    //private Date created_at; we'll use private LocalDateTime created_at; from BaseEntity

    @Enumerated
    private Status account_status;

}
