package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Assessment;
import com.finalproject.BankApplication.model.Customer;
import com.finalproject.BankApplication.service.AssessmentService;
import com.finalproject.BankApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/LKMBank")
public class AssessmentController {

    @Autowired
    AssessmentService assessmentService;

    @Autowired
    CustomerService customerService;

    @GetMapping()
    public String home(){
        return "home";
    }

    @GetMapping("/openAccount")
    public String accountForm(Model model){
        Assessment assessment = new Assessment();
        model.addAttribute("assessment",assessment);
        return "openAccount";
    }

    @PostMapping("/openAccount")
    public String createAssessment(@ModelAttribute Assessment assessment,Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        assessment.setCustomerId(customer.getId());
        customer.setAnnualIncome(assessment.getAnnualIncome());
        assessmentService.saveNew(assessment);
        int id = assessmentService.findLastId();
        assessmentService.submit(id);
        assessmentService.accountType(id);
        //maybe customer id
        String ref = "A" + (12346789 + id);
        model.addAttribute("confirmation","Your account request has been submitted with reference " + ref );
        return "SubmitApplicationConfirmation";
    }

    @GetMapping("/openLoan")
    public String loanForm(Model model){
        Assessment assessment = new Assessment();
        model.addAttribute("assessment",assessment);
        return "openLoan";
    }

    @PostMapping("/openLoan")
    public String createLoanAssessment(@ModelAttribute Assessment assessment,Model model){
        assessmentService.saveNew(assessment);
        int id = assessmentService.findLastId();
        assessmentService.submit(id);
        assessmentService.loanType(id);
        String ref = "L" + (12346789 + id);
        model.addAttribute("confirmation","Your loan request has been submitted with reference " + ref );
        return "SubmitApplicationConfirmation";
    }

    @GetMapping("/checkStatusRequest")
    public String checkStatus() {
        return "findApplicationStatus";
    }

    @PostMapping("/checkStatusRequest")
    public String getStatus(@RequestParam("reference") String reference){
        int refId = Integer.parseInt(reference.substring(1)) - 12346789;
        char type = reference.charAt(0);
        return "redirect:/LKMBank/"+type+"/"+refId;
    }

    @GetMapping(value= "/{type}/{refId}")
    public String statusFound(@PathVariable("refId") int refId, @PathVariable("type") String type, Model model){
        Assessment assessment = assessmentService.findById(refId);
        model.addAttribute("assessment",assessment);
        if (type.equals("A")){
            return "foundAccountStatus";}
        else{
            return "foundLoanStatus";}
    }

    //    TODO: we need to solve problems with Date


    @GetMapping("/admin-console")
    public String adminConsole(Model model){
        return "consoleAdmin";
    }

    @GetMapping("/admin/tellerDashboard")
    public ModelAndView tellerConsole(){
        ModelAndView modelAndView= new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        Map<String, Integer> statistics = assessmentService.statistics();
        modelAndView.addObject("userName", "Welcome Teller: " + customer.getId() +  (" + customer.getEmail() + "));
        modelAndView.addObject("adminMessage","Have a productive day!");
        statistics.forEach((key,value) -> {
            modelAndView.addObject(key,value);
        });
        modelAndView.setViewName("admin/tellerDashboard");
        return modelAndView;}

    // ticket console

    @GetMapping("/ticket-console/opened")
    public String openedTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findOpen();
        model.addAttribute("assessments",assessmentList);
        return "consoleTicketOpened";
    }

    @GetMapping("/ticket-console/pending")
    public String pendingTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findPending();
        model.addAttribute("assessments",assessmentList);
        return "consoleTicketPending";
    }

    @GetMapping("/ticket-console/progress")
    public String progressTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findWIP();
        model.addAttribute("assessments",assessmentList);
        return "consoleTicketProgress";}

    @GetMapping("/ticket-console/completed")
    public String completedTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findDone();
        model.addAttribute("assessments",assessmentList);
        return "consoleTicketCompleted";}

    // account console

    @GetMapping("/account-console")
    public String accountConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findAccountRequest();
        model.addAttribute("assessments",assessmentList);

        return "consoleAccount";
    }

    @GetMapping("/account-console/opened")
    public String openedAccountConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findAccountRequest();
        model.addAttribute("assessments",assessmentList);
        return "consoleAccountOpened";
    }

    @GetMapping("/account-console/pending")
    public String pendingAccountConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findAccountRequest();
        model.addAttribute("assessments",assessmentList);
        return "consoleAccountPending";
    }

    @GetMapping("/account-console/progress")
    public String progressAccountConsole(Model model){
        return "consoleAccountProgress";
    }

    @GetMapping("/account-console/completed")
    public String completedAccountConsole(Model model){
        return "consoleAccountCompleted";
    }

    // loan console

    @GetMapping("/loan-console")
    public String LoanConsole(Model model){
        return "consoleLoan";
    }

    @GetMapping("/loan-console/opened")
    public String openedLoanConsole(Model model){
        return "consoleLoanOpened";
    }

    @GetMapping("/loan-console/pending")
    public String pendingLoanConsole(Model model){
        return "consoleLoanPending";
    }

    @GetMapping("/loan-console/progress")
    public String progressLoanConsole(Model model){
        return "consoleLoanProgress";
    }

    @GetMapping("/loan-console/completed")
    public String completedLoanConsole(Model model){
        return "consoleLoanCompleted";
    }

}
