package com.finalproject.BankApplication.service;

import com.finalproject.BankApplication.model.Assessment;
import com.finalproject.BankApplication.model.AssessmentStatus;
import com.finalproject.BankApplication.model.AssessmentType;
import com.finalproject.BankApplication.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AssessmentService {

    @Autowired
    AssessmentRepository assessmentRepository;

    public void saveNew(Assessment assessment){
        assessmentRepository.save(assessment);
    }

    public int findLastId(){
        return assessmentRepository.findFirstByOrderByIdDesc().getId();
    }

    public Assessment findById(int Id){
        return assessmentRepository.findById(Id).get();
    }

//  Find Assessment

    public List<Assessment> findOpen(){
        List<Assessment> WIPRequestList = findWIP();
        List<Assessment> pendingRequestList = findPending();
        List<Assessment> openedRequestList = Stream.of(WIPRequestList, pendingRequestList)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        return openedRequestList;}

    public List<Assessment> findPending(){
        List<Assessment> pendingRequestList = assessmentRepository.findAssessmentByStatus(AssessmentStatus.PENDING);
        return pendingRequestList;}

    public List<Assessment> findWIP(){
        List<Assessment> WIPRequestList = assessmentRepository.findAssessmentByStatus(AssessmentStatus.IN_PROGRESS);
        return WIPRequestList;}

    public List<Assessment> findDone(){
        List<Assessment> accountRequestList = assessmentRepository.findAssessmentByStatus(AssessmentStatus.DONE);
        return accountRequestList;}

//  Find by type

    public List<Assessment> findOpenRequest(AssessmentType type){
        List<Assessment> openedRequestList = findOpen().
                stream().filter(a->a.getStatus().equals(type)).
                collect(Collectors.toList());
        return openedRequestList;}

    public List<Assessment> findPendingRequest(AssessmentType type){
        List<Assessment> pendingRequestList = findPending().
                stream().filter(a->a.getStatus().equals(type)).
                collect(Collectors.toList());
        return pendingRequestList;}

    public List<Assessment> findWIPRequest(AssessmentType type){
        List<Assessment> WIPRequestList = findWIP().
                stream().filter(a->a.getStatus().equals(type)).
                collect(Collectors.toList());
        return WIPRequestList;}

    public List<Assessment> findDoneRequest(AssessmentType type){
        List<Assessment> doneAccountRequestList = findDone().
                stream().filter(a->a.getStatus().equals(type)).
                collect(Collectors.toList());
        return doneAccountRequestList;}

//  Find Account Request

    public List<Assessment> findAccountRequest(){return assessmentRepository.findAssessmentByType(AssessmentType.ACCOUNT);}

    public List<Assessment> findOpenAccountRequest(){return findOpenRequest(AssessmentType.ACCOUNT);}

    public List<Assessment> findPendingAccountRequest(){return findPendingRequest(AssessmentType.ACCOUNT);}

    public List<Assessment> findWIPAccountRequest(){return findWIPRequest(AssessmentType.ACCOUNT);}

    public List<Assessment> findDoneAccountRequest(){return findDoneRequest(AssessmentType.ACCOUNT);}

//  Find Loan Request

    public List<Assessment> findLoanRequest(){return assessmentRepository.findAssessmentByType(AssessmentType.LOAN);}

    public List<Assessment> findOpenLoanRequest(){return findOpenRequest(AssessmentType.LOAN);}

    public List<Assessment> findPendingLoanRequest(){return findPendingRequest(AssessmentType.LOAN);}

    public List<Assessment> findWIPLoanRequest(){return findWIPRequest(AssessmentType.LOAN);}

    public List<Assessment> findDoneLoanRequest(){return findDoneRequest(AssessmentType.LOAN);}


//    TODO: 1. Update Status 2. Update decision

}
