package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
public class Address extends BaseEntity{

    private String country="";
    private String city="";
    private String postcode="";
    private String street="";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private List<Customer> customers = new ArrayList<>();

    public Address(String country, String city, String postcode, String street) {
        this.country = country;
        this.city = city;
        this.postcode = postcode;
        this.street = street;
    }
    public Address(){}
}
