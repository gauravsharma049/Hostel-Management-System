package com.hostel.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hostel.dto.HostellerDetailsDto;
import com.hostel.service.impl.HostellerDetailsServiceImpl;

@Controller
public class ProfileController {
    @Autowired HostellerDetailsServiceImpl hostellersService;

    @GetMapping("/profile")
    public String profile(Model model){
        
        model.addAttribute("hostellerInfo", hostellersService.findById(11202715));
        return "hostellerProfile";
    }
    @PostMapping("/profile")
    public String profile(@ModelAttribute("hostellerInfo") HostellerDetailsDto hostellerDetailsDto){
        hostellersService.update(hostellerDetailsDto.getHostellerId(), hostellerDetailsDto);
        return "hostellerProfile";
    }
}
