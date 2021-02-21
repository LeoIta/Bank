package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Credential;
import com.finalproject.BankApplication.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @RequestMapping(value={"/","/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public ModelAndView signUp(){
        ModelAndView modelAndView = new ModelAndView();
        Credential credential = new Credential();
        modelAndView.addObject("credential", credential);
        modelAndView.setViewName("signup");
        return modelAndView;

    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
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

    @RequestMapping(value="/admin/dashboard", method = RequestMethod.GET)
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
