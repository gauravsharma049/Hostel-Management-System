package com.hostel.controller.chiefWarden;

import com.hostel.helper.Message;
import com.hostel.model.Hostel;
import com.hostel.service.HostelService;
import com.hostel.service.impl.HostellerDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/warden_c")
public class HostelController {
    @Autowired
    private HostelService hostelService;
    @Autowired
    private HostellerDetailsServiceImpl hostellerDetailsService;
    @GetMapping("/hostel")
    public String hostel(Model model) {
        List<Hostel> hostels = hostelService.findAllHostel();
        model.addAttribute("hostels", hostels);
        model.addAttribute("size", hostels.size());
        model.addAttribute("title", "Manage Hostel");
        return "hostel";
    }

    @GetMapping("/add-hostel")
    public String addForm(Model model) {
        model.addAttribute("title", "Add-Hostel");
        model.addAttribute("hostel", new Hostel());
        return "add-hostel";
    }

    @PostMapping("/add-hostel")
    public String addHostel(@Valid @ModelAttribute("hostel") Hostel hostel,
                            BindingResult bindingResult,
                            HttpSession session,
                            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getErrorCount());
            System.out.println(bindingResult.getAllErrors());
            session.setAttribute("message", new Message("invalid details", "alert-warning"));
            return "add-hostel";
        }
        try {
            hostelService.save(hostel);
            attributes.addFlashAttribute("success", "Added Successfully !");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/warden_c/hostel";
    }

    @GetMapping("/update-hostel/{id}")
    public String updateForm(@PathVariable("id")int id, Model model){
        Hostel hostel = hostelService.findById(id);
        model.addAttribute("hostel",hostel);
        model.addAttribute("title","Update form");
        return "update-hostel";
    }

    @PostMapping("/update-hostel/{id}")
    public String updateProcess(@PathVariable("id")int id,
                                @ModelAttribute("hostel")Hostel hostel,
                                RedirectAttributes attributes){
        try {
            hostel.setHostelId(id);
            hostelService.update(hostel);
            attributes.addFlashAttribute("success","Updated Successfully !");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed","Failed !");
        }
        return "redirect:/warden_c/hostel";
    }

    @GetMapping("/delete-hostel/{id}")
    public String deleteHostel(@PathVariable("id") int id, Model model, RedirectAttributes attributes) {
        try {
            hostelService.delete(id);
            attributes.addFlashAttribute("success", "Deleted Successfully !");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed !");
        }
        return "redirect:/warden_c/hostel";
    }

    @GetMapping("/delete/hosteller/{id}")
    public String delteHosteller(@PathVariable("id") int id, Model model, RedirectAttributes attributes){
        try{
            hostellerDetailsService.delete(id);
            attributes.addFlashAttribute("success", "Deleted Successfully !");
        }
        catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed !");
        }
        return "redirect:/wardens/hostellers-details";
    }
}
