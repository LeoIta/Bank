package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table
public class Income extends TransactionBase {

    private String sender_bank_account;
    private String sender_name;

}
