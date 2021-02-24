package com.finalproject.BankApplication.controller;
/*
import com.finalproject.BankApplication.model.Assessment;
import com.finalproject.BankApplication.model.AssessmentStatus;
import com.finalproject.BankApplication.model.AssessmentType;
import com.finalproject.BankApplication.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/LKMBank")
public class AssessmentController {

    @Autowired
    AssessmentService assessmentService;

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
        assessment.setType(AssessmentType.ACCOUNT);
        assessment.setStatus(AssessmentStatus.PENDING);
        assessmentService.saveNew(assessment);
        int id = assessmentService.findLastId();
        String ref = "A" + (12346789 + id);
        assessmentService.updateTrackingNumberById(id,ref);

        model.addAttribute("confirmation","Your account has been created with reference " + ref );
        return "accountConfirmation";
    }

    @GetMapping("/checkStatusRequest")
    public String checkStatus(){
        return "findRequest";
    }

    @PostMapping("/checkStatusRequest")
    public String getStatus(@RequestParam String trackingNumber, Model model){
        Assessment ass = assessmentService.findTrackingNumber(trackingNumber);
        if(ass==null){
            model.addAttribute("error","Tracking number not found, please check if you type the correct one");
            return "findRequest";}
        return "redirect:/LKMBank/checkStatusRequest/{trackingNumber}";
    }

    @GetMapping("/checkStatusRequest/{trackingNumber}")
    public String myStatus(@RequestParam String trackingNumber){
        char type = trackingNumber.charAt(0);
        String code = trackingNumber.substring(1);
        return "redirect:/LKMBank/checkStatusRequest/{type}/{code}";
    }

    @GetMapping("/checkStatusRequest/A/{code}")
    public String accountStatus(@RequestParam String code){
        String trackingNumber = "A" + code;
        Assessment assessment = assessmentService.findTrackingNumber(trackingNumber);
        return "accountRequest";
    }



}

 */
