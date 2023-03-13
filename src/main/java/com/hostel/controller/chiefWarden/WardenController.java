package com.hostel.controller.chiefWarden;

import com.hostel.model.Hostel;
import com.hostel.model.Warden;
import com.hostel.service.HostelService;
import com.hostel.service.WardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WardenController {

    @Autowired
    private WardenService wardenService;

    @Autowired
    private HostelService hostelService;

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
        List<Hostel> hostels = hostelService.findAllHostel();
        model.addAttribute("hostels",hostels);
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
        return "redirect:/indexWarden";
    }

    @GetMapping("/update-warden/{id}")
    public String updateForm(@PathVariable("id")int id, Model model){
        List<Hostel> hostels = hostelService.findAllHostel();
        Warden warden = wardenService.findById(id);
        model.addAttribute("hostels",hostels);
        model.addAttribute("warden",warden);
        model.addAttribute("title","Update Form");
        return "update-warden";
    }

    @PostMapping("/update-warden/{id}")
    public String updateProcess(@PathVariable("id")int id,
                                @ModelAttribute("warden")Warden warden,
                                RedirectAttributes attributes){
        try {
            warden.setWardenId(id);
            wardenService.update(warden);
            attributes.addFlashAttribute("success","Updated Successfully !");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed","Failed !");
        }
        return "redirect:/indexWarden";
    }

    @GetMapping("/delete-warden/{id}")
    public String deleteWarden(@PathVariable("id")int id, Model model, RedirectAttributes attributes){
        try {
            wardenService.delete(id);
            attributes.addFlashAttribute("success","Deleted Successfully");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed","Failed !");
        }
        return "redirect:/indexWarden";
    }
}
