package com.finalproject.BankApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static com.finalproject.BankApplication.model.AssessmentStatus.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Assessment extends BaseEntity{

    @OneToOne
    private Account accountForm;
    @OneToOne
    private Loan loanForm;

    @Enumerated(EnumType.STRING)
    private Decision decision;

    @Enumerated(EnumType.STRING)
    private AssessmentStatus status;

    public Assessment(Account form) {
        if(!form.isSubmitted()){
            throw new FormNotSubmittedException();
        }
        this.accountForm = form;
        this.status = PENDING;
    }

    public Assessment(Loan form) {
        if(!form.isSubmitted()){
            throw new FormNotSubmittedException();
        }
        this.loanForm = form;
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

}
