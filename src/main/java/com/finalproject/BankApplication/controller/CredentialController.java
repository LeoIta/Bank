package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Credential;
import com.finalproject.BankApplication.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @GetMapping(value={"/","/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value="/signup")
    public ModelAndView signUp(){
        ModelAndView modelAndView = new ModelAndView();
        Credential credential = new Credential();
        modelAndView.addObject("bankId", credential);
        modelAndView.setViewName("signup");
        return modelAndView;

    }

    @PostMapping(value="/signup")
    public ModelAndView createNewCredential(@Validated Credential credential, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Credential credentialExists = credentialService.findCredentialbyId(credential.getBankId());
        if(credentialExists != null){
            bindingResult
                    .rejectValue("bankId","error.credential","The id is already in use.");
        }
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("signup");
        } else {
            credentialService.saveCredential(credential);
            modelAndView.addObject("successMsg", "Singup Successful");
            modelAndView.setViewName("signup");
        }
        return modelAndView;
    }

    @GetMapping(value="/admin/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Credential credential = credentialService.findCredentialbyId(authentication.getName());
        modelAndView.addObject("bankId", "Welcome" + credential.getBankId());
        modelAndView.addObject("adminMsg", "This page is for bank officials only.");
        modelAndView.setViewName("admin/dashboard");
        return modelAndView;
    }

}
