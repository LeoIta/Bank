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

    public Assessment findLastAssessment(){
        return assessmentRepository.findFirstByOrderByIdDesc();
    }

    public int findLastId(){
        return assessmentRepository.findFirstByOrderByIdDesc().getId();
    }

    public Assessment findTrackingNumber(String TrackingNumber){
        return assessmentRepository.findAssessmentByTrackingNumber(TrackingNumber);
    }

    public void updateStatusById(int Id, String status){
        assessmentRepository.updateStatus(Id, status);
    }

    public void updateTypeById(int Id, String type){
        assessmentRepository.updateType(Id, type);
    }

    public void updateTrackingNumberById(int Id, String trackingNumber){
        assessmentRepository.updateTrackingNumber(Id, trackingNumber);
    }
}
