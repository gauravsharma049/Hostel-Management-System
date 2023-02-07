package com.hostel.controller.chiefWarden;

import com.hostel.model.Hostel;
import com.hostel.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HostelController {
    @Autowired
    private HostelService hostelService;

    @GetMapping("/hostel")
    public String hostel(Model model){
        List<Hostel> hostels = hostelService.findAllHostel();
        model.addAttribute("hostels",hostels);
        model.addAttribute("size",hostels.size());
        model.addAttribute("title","Manage Hostel");
        model.addAttribute("hostelNew",new Hostel());
        return "hostel";
    }

    @PostMapping("add-hostel")
    public String addHostel(@ModelAttribute("hostelNew")Hostel hostel, RedirectAttributes attributes){
        try {
            hostelService.save(hostel);
            attributes.addFlashAttribute("success","Added Successfully !");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed","Failed");
        }
        return "redirect:/hostel";
    }

}
