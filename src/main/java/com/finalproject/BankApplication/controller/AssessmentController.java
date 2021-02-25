package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Assessment;
import com.finalproject.BankApplication.model.AssessmentStatus;
import com.finalproject.BankApplication.model.AssessmentType;
import com.finalproject.BankApplication.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AssessmentController {

    @Autowired
    AssessmentService assessmentService;

    /*@GetMapping("/")
    public String home(){
        return "home";
    }*/

    @GetMapping("/user/openAccount")
    public String accountForm(Model model){
        Assessment assessment = new Assessment();
        model.addAttribute("assessment",assessment);
        return "openAccount";
    }

    @PostMapping("/user/openAccount")
    public String createAssessment(@ModelAttribute Assessment assessment,Model model){
        assessment.setType(AssessmentType.ACCOUNT);
        assessment.setStatus(AssessmentStatus.PENDING);
        assessmentService.saveNew(assessment);
        int id = assessmentService.findLastId();
        String ref = "A" + (12346789 + id);
        model.addAttribute("confirmation","Your account request has been submitted with reference " + ref );
        return "SubmitApplicationConfirmation";
    }

    @GetMapping("/user/openLoan")
    public String loanForm(Model model){
        Assessment assessment = new Assessment();
        model.addAttribute("assessment",assessment);
        return "openLoan";
    }

    @PostMapping("/user/openLoan")
    public String createLoanAssessment(@ModelAttribute Assessment assessment,Model model){
        assessment.setType(AssessmentType.LOAN);
        assessment.setStatus(AssessmentStatus.PENDING);
        assessmentService.saveNew(assessment);
        int id = assessmentService.findLastId();
        String ref = "L" + (12346789 + id);
        model.addAttribute("confirmation","Your loan request has been submitted with reference " + ref );
        return "SubmitApplicationConfirmation";
    }

    @GetMapping("/user/checkStatusRequest")
    public String checkStatus() {
        return "findApplicationStatus";
    }

    @PostMapping("/user/checkStatusRequest")
    public String getStatus(@RequestParam("reference") String reference){
        int refId = Integer.parseInt(reference.substring(1)) - 12346789;
        char type = reference.charAt(0);
        return "redirect:/LKMBank/"+type+"/"+refId;
    }

    @GetMapping(value= "/user/{type}/{refId}")
    public String statusFound(@PathVariable("refId") int refId, @PathVariable("type") String type, Model model){
        Assessment assessment = assessmentService.findById(refId);
        model.addAttribute("assessment",assessment);
        if (type.equals("A")){
            return "foundAccountStatus";}
        else{
            return "foundLoanStatus";}
    }


    //    TODO: we need to solve two problems 1. using Date and 2. SQL DML
}
