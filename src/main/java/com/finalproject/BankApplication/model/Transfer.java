package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="transfers")
public class Transfer extends Transaction{

    private String recipient_bank_account;
    private String recipient_name;

    @Enumerated
    private Status status;

    //private Date transfer_time; we'll use private LocalDateTime created_at; from BaseEntity
}
