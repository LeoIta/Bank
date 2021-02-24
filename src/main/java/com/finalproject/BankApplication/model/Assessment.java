package com.finalproject.BankApplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import static com.finalproject.BankApplication.model.AssessmentStatus.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Assessment extends TimeEntity{

    private String firstName;
    private String lastName;
    private String email;

//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    private Date dateOfBirth;

    private int annualIncome;
    private int firstDeposit;
    private String country;
    private String city;
    private String postcode;
    private String street;

    @Enumerated(EnumType.STRING)
    private AssessmentType type;

    @Enumerated(EnumType.STRING)
    private AssessmentStatus status;

    @Enumerated(EnumType.STRING)
    private Decision decision;


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

}