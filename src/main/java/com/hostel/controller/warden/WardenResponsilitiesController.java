package com.hostel.controller.warden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hostel.dto.HostellerDetailsDto;
import com.hostel.service.impl.HostellerDetailsServiceImpl;
import com.hostel.service.impl.UserServiceImpl;

@Controller
public class WardenResponsilitiesController {
    @Autowired UserServiceImpl userService;
    @Autowired HostellerDetailsServiceImpl hostellerDetailsService;

    @PostMapping("/add-hosteller")
    public String addHosteller(@ModelAttribute("hostellerDetails") HostellerDetailsDto hostllerDetailsDto) {
        hostellerDetailsService.save(hostllerDetailsDto);
        return "addHosteller";
    }

    @GetMapping("/add-hosteller")
    public String addHostellerForm(Model model) {
        model.addAttribute("hostellerDetails", new HostellerDetailsDto());
        return "addHosteller";
    }
}
