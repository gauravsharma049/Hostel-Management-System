package com.hostel.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hostel.dto.UserDto;
import com.hostel.service.impl.UserServiceImpl;

@ControllerAdvice
public class GlobalInfo {
    @Autowired UserServiceImpl userService;
    @ModelAttribute("loginInfo")
    public void loginInfo(Model model, Principal principal) {
        try{
            String userName = principal.getName();
            System.out.println("USERNAME: " + userName);
            UserDto loggedInUser = userService.findByEmail(userName);
            model.addAttribute("loginInfo", loggedInUser);
        }

        catch(Exception e){
            model.addAttribute("loginInfo", "anonymous");
            System.out.println("error");
        }
    }
    
    
}
