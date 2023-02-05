package com.hostel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
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
    @GetMapping("/indexHostel")
    public String indexHostel(){
        return "hostel";
    }

}
