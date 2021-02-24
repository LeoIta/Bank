/*
package com.finalproject.BankApplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static com.finalproject.BankApplication.model.AssessmentStatus.*;


@Getter
@Setter
@NoArgsConstructor
@Table
@Entity
public class Assessment extends TimeEntity{

    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private int annualIncome;
    private int firstDeposit;
    private String country;
    private String city;
    private String postcode;
    private String street;
    private AssessmentType type;

    @OneToOne
    private int tellerId;

    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private AssessmentStatus status;

    @Enumerated(EnumType.STRING)
    private Decision decision;

    public Assessment(String firstName, String lastName, String email,
                      int annualIncome, int firstDeposit, String country, String city,
                      String postcode, String street, AssessmentType type, AssessmentStatus status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
//        this.dateOfBirth = dateOfBirth;
        this.annualIncome = annualIncome;
        this.firstDeposit = firstDeposit;
        this.country = country;
        this.city = city;
        this.postcode = postcode;
        this.street = street;
        this.type = type;
        this.status = status;
    }

    public void start(){
        if (status==PENDING) {
            status = IN_PROGRESS;
        }else{
            throw new InvalidAssessmentStatusException();
        }
    }

    public void makeDecision(Decision decision){
        if (status==IN_PROGRESS) {
            this.decision = decision;
            status = DONE;
        }else{
            throw new InvalidAssessmentStatusException();
        }
    }

}*/
