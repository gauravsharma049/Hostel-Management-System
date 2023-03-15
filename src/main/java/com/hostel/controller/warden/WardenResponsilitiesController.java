package com.hostel.controller.warden;

import com.hostel.model.HostellerDetails;
import com.hostel.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostel.dto.HostellerDetailsDto;
import com.hostel.service.impl.HostellerDetailsServiceImpl;
import com.hostel.service.impl.UserServiceImpl;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/wardens")
public class WardenResponsilitiesController {
    @Autowired UserServiceImpl userService;
    @Autowired HostellerDetailsServiceImpl hostellerDetailsService;

    @PostMapping("/add-hosteller")
    public String addHosteller(@ModelAttribute("hostellerDetails") HostellerDetailsDto hostllerDetailsDto, RedirectAttributes attributes) {
       try {
           hostellerDetailsService.save(hostllerDetailsDto);
           attributes.addFlashAttribute("success", "Added Successfully !");
       }
       catch (Exception e){
           attributes.addFlashAttribute("failed", "Something went wrong !");
       }
//        System.out.println(hostllerDetailsDto.getUser().getEmail());
        return "redirect:/wardens/hostellers-details";
    }

    @GetMapping("/add-hosteller")
    public String addHostellerForm(Model model) {
        User user = new User();
        HostellerDetailsDto hostellerDetailsDto = new HostellerDetailsDto();
        hostellerDetailsDto.setUser(user);
        model.addAttribute("hostellerDetails", hostellerDetailsDto);
        return "addHosteller";
    }

    @GetMapping("/hostellers-details")
    public String hostellersDetails(Model model) {
        model.addAttribute("hostellers", hostellerDetailsService.findAllHostellerDetails());
        return "hostellersDetails";
    }
}
