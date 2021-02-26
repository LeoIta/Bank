package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@Table
public class Customer extends BaseEntity{

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    @OneToOne(targetEntity = Account.class)
    private Account account;

    /*private Date dateOfBirth;*/

    private Long annualIncome;

    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;

    @Column(name = "ACTIVE")
    private int active;

    private String mail;
}

