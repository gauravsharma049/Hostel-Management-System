package com.hostel.controller.chiefWarden;

import com.hostel.helper.Message;
import com.hostel.model.Hostel;
import com.hostel.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
        return "hostel";
    }

    @GetMapping("/add-hostel")
    public String addForm(Model model){
        model.addAttribute("title","Add-Hostel");
        model.addAttribute("hostel",new Hostel());
        return "add-hostel";
    }

    @PostMapping("/save-hostel")
    public String addHostel(@Valid @ModelAttribute("hostel")Hostel hostel,
                            BindingResult bindingResult,
                            HttpSession session,
                            RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getErrorCount());
            System.out.println(bindingResult.getAllErrors());
            session.setAttribute("message", new Message("invalid details", "alert-warning"));
            return "add-hostel";
        }
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
