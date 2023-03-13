package com.hostel.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hostel.dto.UserDto;
import com.hostel.model.HostellerDetails;
import com.hostel.model.Role;
import com.hostel.service.impl.HostellerDetailsServiceImpl;
import com.hostel.service.impl.UserServiceImpl;

@ControllerAdvice
public class GlobalInfo {
    @Autowired UserServiceImpl userService;
    @Autowired HostellerDetailsServiceImpl hostellerDetailsService;


    @ModelAttribute("loginInfo")
    public void loginInfo(Model model, Principal principal) {
        try{
            String userName = principal.getName();
            System.out.println("USERNAME: " + userName);
            UserDto loggedInUser = userService.findByEmail(userName);
            List<Integer> loggedInUserRoles = new ArrayList<>();
            for(Role role : loggedInUser.getRoles()){
                loggedInUserRoles.add(role.getId());
            }
            HostellerDetails hostellerProfile = new HostellerDetails();
            for(Role  role : loggedInUser.getRoles()){
                if(role.getId() == 4){
                    hostellerProfile = hostellerDetailsService.findByUserId(loggedInUser.getUserId());
                }
            }
            // System.out.println(hostellerDetailsService.findByUserId(loggedInUser.getUserId()).getHostellerId());
            System.out.println(loggedInUser);
            model.addAttribute("hostellerProfile", hostellerProfile);
            model.addAttribute("loginInfo", loggedInUser);
            model.addAttribute("loginInfoRoles", loggedInUserRoles);
        }

        catch(Exception e){
            model.addAttribute("loginInfo", "anonymous");
            System.out.println("error");
        }
    }
    
    
}
