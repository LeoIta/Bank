package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.*;
import com.finalproject.BankApplication.service.AddressService;
import com.finalproject.BankApplication.service.AssessmentService;
import com.finalproject.BankApplication.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class AssessmentController {

    AssessmentService assessmentService;
    CustomerService customerService;
    AddressService addressService;


    @GetMapping("/customer/openAccount")
    public String accountForm(Model model){
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
        calendar.add(Calendar.YEAR, -18);
        String birth = (new SimpleDateFormat("yyyy-MM-dd")
                .format(calendar.getTime()));
        model.addAttribute("birth",birth);
        calendar.add(Calendar.YEAR, -82);
        String oldest = (new SimpleDateFormat("yyyy-MM-dd")
                .format(calendar.getTime()));
        model.addAttribute("birth",birth);
        model.addAttribute("oldest",oldest);
        // set limit date*/
        model.addAttribute("zero",0);
        model.addAttribute("minDeposit",50);
        model.addAttribute("assessment",assessment);
        return "customer/openAccount";
    }

    @PostMapping("/customer/openAccount")
    public String createAssessment(@ModelAttribute Assessment assessment, Model model){

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        Address address = new Address();
        Integer customerId = customer.getId();
        address.setCity(assessment.getCity());
        address.setCountry(assessment.getCountry());
        address.setPostcode(assessment.getPostcode());
        address.setStreet(assessment.getStreet());
        address.setCustomerId(customerId);
        addressService.saveAddress(address);
        assessment.setCustomerId(customer.getId());
        customer.setAnnualIncome(assessment.getAnnualIncome());
        assessmentService.saveNew(assessment);
        int id = assessmentService.findLastId();
        assessmentService.changeType(AssessmentType.ACCOUNT,id);
        String ref = "A" + (12346789 + id);
        model.addAttribute("confirmation","Your account request has been submitted with reference " + ref );
        return "submitApplicationConfirmation";
    }

    @GetMapping("/checkStatusRequest")
    public String checkStatus(HttpServletRequest request, Model model) {
        model.addAttribute("back",request.getHeader("Referer"));
        return "findApplicationStatus";
    }

    @PostMapping("/checkStatusRequest")
    public String getStatus(@RequestParam("reference") String reference,HttpServletRequest request,
                            Model model){
        int refId = Integer.parseInt(reference.substring(1)) - 12346789;
        char type = reference.charAt(0);
        model.addAttribute("back",request.getHeader("Referer"));
        return "redirect:/" + type + "/" + refId;
    }

    @GetMapping(value= "/{type}/{refId}")
    public String statusFound(@PathVariable("refId") int refId, @PathVariable("type") String type,
                              HttpServletRequest request, Model model){
        Assessment assessment = assessmentService.findById(refId);
        model.addAttribute("back",request.getHeader("Referer"));
        model.addAttribute("assessment",assessment);
        return "foundReqStatus";
    }

    @GetMapping("/admin/tellerDashboard")
    public String tellerConsole(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        Map<String, Integer> statistics = assessmentService.statistics();
        model.addAttribute("userName", "Welcome " + customer.getFirstName());
        model.addAttribute("userMessage","Have a productive day!");
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
        List<Assessment> assessmentList = assessmentService.findAccountRequestOpen();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleAccountOpened";
    }

    @GetMapping("admin/account-console/pending")
    public String pendingAccountConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findAccountRequestPending();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleAccountPending";
    }

    @GetMapping("admin/account-console/progress")
    public String progressAccountConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findAccountRequestWIP();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleAccountProgress";
    }

    @GetMapping("admin/account-console/completed")
    public String completedAccountConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findAccountRequestDone();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleAccountCompleted";
    }

    // loan console

    @GetMapping("admin/loan-console")
    public String loanConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findLoanRequest();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleLoan";
    }

    @GetMapping("admin/loan-console/opened")
    public String openedLoanConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findLoanRequestOpen();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleLoanOpened";
    }

    @GetMapping("admin/loan-console/pending")
    public String pendingLoanConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findLoanRequestPending();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleLoanPending";
    }

    @GetMapping("admin/loan-console/progress")
    public String progressLoanConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findLoanRequestWIP();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleLoanProgress";
    }

    @GetMapping("admin/loan-console/completed")
    public String completedLoanConsole(Model model){
        List<Assessment> assessmentList = assessmentService.findLoanRequestDone();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleLoanCompleted";
    }

    @GetMapping(value="admin/{action}/{id}")
    public String details( @PathVariable String action,
                           @PathVariable int id, Model model, HttpServletRequest request) {
        Assessment assessment = assessmentService.findById(id);
        String back = request.getHeader("Referer");
        String colors;
        String req;
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime localDateTime = Instant
                .ofEpochMilli(assessment.getModifiedAt().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        String completedDate = localDateTime.format(myFormat);
        if (assessment.getDecision() == Decision.APPROVED) {
            colors = "background-color:green";
            req = "  APPROVED ON " + completedDate;
        } else if (assessment.getDecision() == Decision.REJECTED){
            colors = "background-color:red";
            req = "  REJECTED ON " + completedDate;
        }else{
            colors = "background-color:transparent";
            req = "  REQUEST DETAILS";
        }
        model.addAttribute("colors",colors);
        model.addAttribute("req",req);
        model.addAttribute("back",back);
        if (action.equals("start") &&
                assessment.getStatus() == AssessmentStatus.PENDING) {
            assessmentService.start(id);
            model.addAttribute("assessment", assessment);
            return "admin/details";
        }
        model.addAttribute("assessment",assessment);
        return "admin/details";
    }

    @GetMapping("/admin/account/{decision}/{id}")
    public String accountApproved(@PathVariable String decision, @PathVariable int id, Model model, HttpServletRequest request){
        Assessment assessment = assessmentService.findById(id);
        if (decision.equals("approved")){
            assessment.setDecision(Decision.APPROVED);
            assessment.setStatus(AssessmentStatus.DONE);
            Account account = new Account();
            String ref = "LKM" + (12346789 + "A" + assessment.getCustomerId());
            account.setAccountNumber(ref);
            model.addAttribute("confirmation","Request successfully approved");
        }else{
            assessment.setDecision(Decision.REJECTED);
            assessment.setStatus(AssessmentStatus.DONE);
            model.addAttribute("confirmation","Request successfully rejected");
        }
        return "done";}

}


