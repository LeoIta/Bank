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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class AssessmentController {

    AssessmentService assessmentService;
    CustomerService customerService;



    @GetMapping("/checkStatusRequest")
    public String checkStatus() {
        return "findApplicationStatus";
    }

    @PostMapping("/checkStatusRequest")
    public String getStatus(@RequestParam("reference") String reference){
        int refId = Integer.parseInt(reference.substring(1)) - 12346789;
        char type = reference.charAt(0);
        return "redirect:/"+type+"/"+refId;
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

    //    TODO: admin/console and children

    @GetMapping("/admin/tellerDashboard")
    public String tellerConsole(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        Map<String, Integer> statistics = assessmentService.statistics();
        model.addAttribute("userName", "Welcome " + customer.getFirstName());
        model.addAttribute("adminMessage","Have a productive day!");
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        return "admin/tellerDashboard";}

    // ticket console

    @GetMapping("admin/ticket-console/opened")
    public String openedTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findOpen();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleTicketOpened";
    }

    @GetMapping("admin/ticket-console/pending")
    public String pendingTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findPending();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleTicketPending";
    }

    @GetMapping("admin/ticket-console/progress")
    public String progressTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findWIP();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleTicketProgress";}

    @GetMapping("admin/ticket-console/completed")
    public String completedTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findDone();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleTicketCompleted";}

    // account console

    @GetMapping("admin/account-console")
    public String accountConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findAccountRequest();
        model.addAttribute("assessments",assessmentList);

        return "admin/consoleAccount";
    }

    @GetMapping("admin/account-console/opened")
    public String openedAccountConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findAccountRequest();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleAccountOpened";
    }

    @GetMapping("admin/account-console/pending")
    public String pendingAccountConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findAccountRequest();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleAccountPending";
    }

    @GetMapping("admin/account-console/progress")
    public String progressAccountConsole(Model model){
        return "admin/consoleAccountProgress";
    }

    @GetMapping("admin/account-console/completed")
    public String completedAccountConsole(Model model){
        return "admin/consoleAccountCompleted";
    }

    // loan console

    @GetMapping("admin/loan-console")
    public String loanConsole(Model model){
        return "admin/consoleLoan";
    }

    @GetMapping("admin/loan-console/opened")
    public String openedLoanConsole(Model model){
        return "admin/consoleLoanOpened";
    }

    @GetMapping("admin/loan-console/pending")
    public String pendingLoanConsole(Model model){
        return "admin/consoleLoanPending";
    }

    @GetMapping("admin/loan-console/progress")
    public String progressLoanConsole(Model model){
        return "admin/consoleLoanProgress";
    }

    @GetMapping("admin/loan-console/completed")
    public String completedLoanConsole(Model model){
        return "admin/consoleLoanCompleted";
    }

}
