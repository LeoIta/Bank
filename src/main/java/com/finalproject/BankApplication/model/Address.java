package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table
public class Address extends BaseEntity{

    @Column
    private Integer customerId;

    private String country;
    private String city;
    private String postcode;
    private String street;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressId")
    private Set<Customer> customers = new HashSet<>();
}
