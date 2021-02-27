
package com.finalproject.BankApplication.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Data
@Table
public class Assessment extends TimeEntity{

    private String firstName;
    private String lastName;
    private String email;


//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    private LocalDateTime dateOfBirth;

    private long annualIncome=0;
    private int firstDeposit=0;
    private String country="";
    private String city="";
    private String postcode="";
    private String street="";
    private int payDay=1;
    private String reason="";
    private long amount=0;
    private int customerId;

//    private LocalDateTime startDate;
//    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private AssessmentType type;

    @Enumerated(EnumType.STRING)
    private AssessmentStatus status;

    @Enumerated(EnumType.STRING)
    private Decision decision;


}