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

    private String annualIncome;

<<<<<<< HEAD
=======
    
>>>>>>> main
    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;

<<<<<<< HEAD
    @Column(name = "ACTIVE")
    private int active;
=======

    @Column(name = "ACTIVE")
    private int active;

    private String mail;

>>>>>>> main
}

