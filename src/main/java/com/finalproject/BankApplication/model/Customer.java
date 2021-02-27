package com.finalproject.BankApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Customer extends PersonEntity{


    @OneToOne(targetEntity = Account.class)
    private Account account;

    /*private Date dateOfBirth;*/

    private Long annualIncome;


    private Integer addressId;

    @Column(name = "ACTIVE")
    private Integer active;
}

