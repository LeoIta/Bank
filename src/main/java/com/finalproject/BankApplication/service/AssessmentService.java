package com.finalproject.BankApplication.service;

import com.finalproject.BankApplication.model.Assessment;
import com.finalproject.BankApplication.reposiitory.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
