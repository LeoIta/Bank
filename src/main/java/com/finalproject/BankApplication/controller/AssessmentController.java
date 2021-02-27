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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class AssessmentController {

    AssessmentService assessmentService;
    CustomerService customerService;

    @GetMapping("//LKMBank")
    public String home(){
        return "home";
    }

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

    //    TODO: we need to solve problems with Date


    @GetMapping("/admin/admin-console")
    public String adminConsole(Model model){
        return "tellerDashboard";
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
