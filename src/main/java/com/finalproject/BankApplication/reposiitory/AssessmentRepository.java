package com.finalproject.BankApplication.reposiitory;

import com.finalproject.BankApplication.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Integer> {
    public Assessment findFirstByOrderByIdDesc();
    public Assessment findAssessmentById(int id);

}
