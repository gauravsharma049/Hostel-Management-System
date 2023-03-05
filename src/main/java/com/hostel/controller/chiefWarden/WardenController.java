package com.hostel.controller.chiefWarden;

import com.hostel.model.Warden;
import com.hostel.service.WardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WardenController {

    @Autowired
    private WardenService wardenService;

    @GetMapping("/indexWarden")
    public String warden(Model model){
        List<Warden> wardens = wardenService.findAllWarden();
        model.addAttribute("wardens",wardens);
        model.addAttribute("size",wardens.size());
        model.addAttribute("title","Manage Warden");
        return "warden";
    }

    @GetMapping("/add-warden")
    public String addForm(Model model){
        model.addAttribute("title","Add Warden");
        model.addAttribute("warden", new Warden());
        return "add-warden";
    }

    @PostMapping("/save-warden")
    public String addWarden(@ModelAttribute("warden")Warden warden,
                            HttpSession session,
                            RedirectAttributes attributes){
        try{
            wardenService.save(warden);
            attributes.addFlashAttribute("success","Added Successfully");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed","Failed");
        }
        return "redirect:/warden";
    }

}
