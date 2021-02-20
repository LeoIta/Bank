package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table
public class Loan extends TransactionBase {
    //also Loan_Request should have reason
    private Date start_date;
    private Date due_date;
    private int pay_day;

    @Enumerated(EnumType.STRING)
    private Status status;
}
