package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table
public class Income extends Transaction {

    private String sender_bank_account;
    private String sender_name;

    //private String transfer_time; we'll use private LocalDateTime created_at; from BaseEntity

}
