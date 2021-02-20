package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table
public class Client extends BaseEntity{

    private String first_name;
    private String last_name;
    private Date date_of_birth;
    private String annual_income;

    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;

    private String mail;
}

