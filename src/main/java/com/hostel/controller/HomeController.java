package com.hostel.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.hostel.helper.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hostel.dto.UserDto;
import com.hostel.service.impl.UserServiceImpl;

@Controller
public class HomeController {
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/")
    public String home() {
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @GetMapping("/not-found")
    public String notFound() {
        return "404.html";
    }
    @GetMapping("/forgotten-password")
    public String forgotPassword(){
        return "forgot-password";
    }
    @GetMapping("/indexWarden")
    public String indexWarden(){
        return "warden";
    }

    @PostMapping("/register")
    public String SignUp(@Valid @ModelAttribute("user") UserDto userDto,
                         BindingResult bindingResult,
                         Model model,
                         HttpSession session){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getErrorCount());
            System.out.println(bindingResult.getAllErrors());
            session.setAttribute("message", new Message("invalid details", "alert-warning"));
            return "register";
        }
        try{

            System.out.println(userDto);
            userService.save(userDto);
            model.addAttribute("user", new UserDto());
            session.setAttribute("message", new Message("signup successful!!", "alert-success"));
            return "register";

        }
        catch(DataIntegrityViolationException e){
            session.setAttribute("message", new Message("user with email id "+userDto.getEmail() + " already exists..", "alert-warning"));
            return "register";
        }
        catch(Exception e){
            model.addAttribute("user", userDto);
            session.setAttribute("message", new Message("something went wrong!!! "+ e.getMessage(), "alert-danger"));
            e.printStackTrace();
            return "register";
        }
        // userService.save(userDto);
    }
}
