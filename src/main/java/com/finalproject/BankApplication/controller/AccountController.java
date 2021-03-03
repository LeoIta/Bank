package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Assessment;
import com.finalproject.BankApplication.model.AssessmentType;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@AllArgsConstructor
public class AccountController {

    CustomerService customerService;
    AssessmentService assessmentService;


    @GetMapping("/customer/openLoan")
    public String loggedLoan(Model model){
        Authentication auth = SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        Assessment assessment = new Assessment();
        assessment.setFirstName(customer.getFirstName());
        assessment.setLastName(customer.getLastName());
        assessment.setEmail(customer.getEmail());
        ///*set limit date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        String start = (new SimpleDateFormat("YYYY-MM-dd")
                .format(calendar.getTime()))
                .toString();
        calendar.add(Calendar.MONTH, 1);
        String due = (new SimpleDateFormat("YYYY-MM-dd")
                .format(calendar.getTime()))
                .toString();
        model.addAttribute("start",start);
        model.addAttribute("due",due);
        model.addAttribute("minDay",1);
        model.addAttribute("maxDay",28);
        model.addAttribute("zero",0);
        model.addAttribute("minLoan",1000);
        // set limit date*/
        model.addAttribute("assessment",assessment);
        return "customer/openLoan";
    }

    @PostMapping("/customer/openLoan")
    public String createLoggedLoan(@ModelAttribute Assessment assessment, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        assessment.setCustomerId(customer.getId());
        customer.setAnnualIncome(assessment.getAnnualIncome());
        assessmentService.saveNew(assessment);
        int id = assessmentService.findLastId();
        assessmentService.changeType(AssessmentType.LOAN,id);
        String ref = "L" + (12346789 + id);
        model.addAttribute("confirmation","Your loan request has been submitted with reference " + ref );
        return "SubmitApplicationConfirmation";
    }
}
