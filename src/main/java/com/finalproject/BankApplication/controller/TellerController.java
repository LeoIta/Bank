package com.finalproject.BankApplication.controller;


import com.finalproject.BankApplication.model.Assessment;
import com.finalproject.BankApplication.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/LKMBank/bank")
public class TellerController {

    //@Autowired
    private AssessmentService assessmentService;

    @GetMapping("/{tellerId}")
        public String tellerDashboard(){
        return "redirect:/LKMBank/bank/{tellerId}/ticket-console";
}

    @GetMapping("/{tellerId}/admin-console")
    public String adminConsole(Model model){
        return "consoleAdmin";
    }

    @GetMapping("/{tellerId}/ticket-console")
    public String tellerConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        return "dashboardTeller";}

    // ticket console
    
    @GetMapping("/{tellerId}/ticket-console/opened")
    public String openedTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findOpen();
        model.addAttribute(assessmentList);
        return "consoleTicketOpened";
    }

    @GetMapping("/{tellerId}/ticket-console/pending")
    public String pendingTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findPending();
        model.addAttribute(assessmentList);
        return "consoleTicketPending";
    }

    @GetMapping("/{tellerId}/ticket-console/progress")
    public String progressTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findWIP();
        model.addAttribute(assessmentList);
        return "consoleTicketProgress";}

    @GetMapping("/{tellerId}/ticket-console/completed")
    public String completedTicketConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findDone();
        model.addAttribute(assessmentList);
        return "consoleTicketCompleted";}

    // account console

    @GetMapping("/{tellerId}/account-console")
    public String accountConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findAccountRequest();
        model.addAttribute(assessmentList);
        return "consoleAccount";
    }

    @GetMapping("/{tellerId}/account-console/opened")
    public String openedAccountConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findAccountRequest();
        model.addAttribute(assessmentList);
        return "consoleAccountOpened";
    }
    
    @GetMapping("/{tellerId}/account-console/pending")
    public String pendingAccountConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findAccountRequest();
        model.addAttribute(assessmentList);
        return "consoleAccountPending";
    }
    
    @GetMapping("/{tellerId}/account-console/progress")
    public String progressAccountConsole(Model model){
        return "consoleAccountProgress";
    }
    
    @GetMapping("/{tellerId}/account-console/completed")
    public String completedAccountConsole(Model model){
        return "consoleAccountCompleted";
    }

    // loan console

    @GetMapping("/{tellerId}/loan-console")
    public String LoanConsole(Model model){
        return "consoleLoan";
    }

    @GetMapping("/{tellerId}/loan-console/opened")
    public String openedLoanConsole(Model model){
        return "consoleLoanOpened";
    }

    @GetMapping("/{tellerId}/loan-console/pending")
    public String pendingLoanConsole(Model model){
        return "consoleLoanPending";
    }

    @GetMapping("/{tellerId}/loan-console/progress")
    public String progressLoanConsole(Model model){
        return "consoleLoanProgress";
    }

    @GetMapping("/{tellerId}/loan-console/completed")
    public String completedLoanConsole(Model model){
        return "consoleLoanCompleted";
    }

}

