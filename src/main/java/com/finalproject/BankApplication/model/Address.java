package com.finalproject.BankApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Address extends BaseEntity{

    private String country;
    private String city;
    private String postcode;
    private String street;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")

    private Set<Customer> customers = new HashSet<>();

    private List<Customer> customers = new ArrayList<>();

}
