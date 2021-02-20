package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="loan_requests")
public class Loan_Request extends TransactionBase {
    //also Loan_Request should have reason
    private Date start_date;
    private Date due_date;
    private int pay_day;

    @Enumerated(EnumType.STRING)
    private Status status;
}
