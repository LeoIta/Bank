package com.finalproject.BankApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Loan extends BaseEntity{
    private long amount;
    private String reason;

    @OneToOne(targetEntity = Account.class)
    private Account account;

    private Date startDate;
    private Date dueDate;
    private int payDay;

    @Enumerated(EnumType.STRING)
    private FormStatus loanStatus;

    private Assessment assessment;

    public Loan(long amount, String reason, Account account, Date startDate,
                Date due_date, int payDay) {
        this.amount = amount;
        this.reason = reason;
        this.account = account;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.payDay = payDay;
        this.loanStatus = FormStatus.SUBMITTED;
    }

    public boolean isSubmitted() {
        return loanStatus == FormStatus.SUBMITTED;
    }
}
