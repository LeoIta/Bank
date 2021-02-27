package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Assessment;
import com.finalproject.BankApplication.model.Customer;
import com.finalproject.BankApplication.service.AssessmentService;
import com.finalproject.BankApplication.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AccountController {

    CustomerService customerService;
    AssessmentService assessmentService;

    @GetMapping("/customer/openAccount")
    public String accountForm(Model model){
        Assessment assessment = new Assessment();
        model.addAttribute("assessment",assessment);
        return "customer/openAccount";
    }

    @PostMapping("/customer/openAccount")
    public String createAssessment(@ModelAttribute Assessment assessment, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        assessment.setCustomerId(customer.getId());
        customer.setAnnualIncome(assessment.getAnnualIncome());
        assessmentService.saveNew(assessment);
        int id = assessmentService.findLastId();
        assessmentService.submit(id);
        assessmentService.accountType(id);
        String ref = "A" + (12346789 + id);
        model.addAttribute("confirmation","Your account request has been submitted with reference " + ref );
        return "SubmitApplicationConfirmation";
    }

    @GetMapping("/customer/loan")
    public String loggedLoan(Model model){
        Assessment assessment = new Assessment();
        model.addAttribute("assessment",assessment);
        return "redirect:/customer/loan";
    }

    @PostMapping("/customer/loan")
    public String createLoggedLoan(@ModelAttribute Assessment assessment,Model model){
        assessmentService.saveNew(assessment);
        int id = assessmentService.findLastId();
        assessmentService.submit(id);
        assessmentService.loanType(id);
        String ref = "L" + (12346789 + id);
        model.addAttribute("confirmation","Your loan request has been submitted with reference " + ref );
        return "SubmitApplicationConfirmation";
    }
    @GetMapping("/loan")
    public String loanForm(Model model){
        Assessment assessment = new Assessment();
        model.addAttribute("assessment",assessment);
        return "loan";
    }

    @PostMapping("/loan")
    public String createLoanAssessment(@ModelAttribute Assessment assessment,Model model){
        assessmentService.saveNew(assessment);
        int id = assessmentService.findLastId();
        assessmentService.submit(id);
        assessmentService.loanType(id);
        String ref = "L" + (12346789 + id);
        model.addAttribute("confirmation","Your loan request has been submitted with reference " + ref );
        return "submitApplicationConfirmation";
    }
}
