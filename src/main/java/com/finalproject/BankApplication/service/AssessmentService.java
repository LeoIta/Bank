package com.finalproject.BankApplication.service;

import com.finalproject.BankApplication.model.Assessment;
import com.finalproject.BankApplication.model.AssessmentStatus;
import com.finalproject.BankApplication.model.AssessmentType;
import com.finalproject.BankApplication.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public interface AssessmentService {
    public void saveNew(Assessment assessment);

    public int findLastId();

    public Assessment findById(int Id);


//  Find Assessment

    public List<Assessment> findAll();

    public List<Assessment> findOpen();

    public List<Assessment> findPending();

    public List<Assessment> findWIP();

    public List<Assessment> findDone();



//  Find by type

    public List<Assessment> findOpenRequest(AssessmentType type);

    public List<Assessment> findPendingRequest(AssessmentType type);

    public List<Assessment> findWIPRequest(AssessmentType type);

    public List<Assessment> findDoneRequest(AssessmentType type);


//  Find Account Request

    public List<Assessment> findAccountRequest();

    public List<Assessment> findAccountRequestOpen();

    public List<Assessment> findAccountRequestPending();

    public List<Assessment> findAccountRequestWIP();

    public List<Assessment> findAccountRequestDone();

//  Find Loan Request

    public List<Assessment> findLoanRequest();

    public List<Assessment> findLoanRequestOpen();
    public List<Assessment> findLoanRequestPending();
    public List<Assessment> findLoanRequestWIP();
    public List<Assessment> findLoanRequestDone();
    public Map<String, Integer> statistics();







//    TODO: 1. Update Status 2. Update decision

}
