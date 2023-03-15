package com.hostel.controller.feesPayment;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hostel.dto.FeesPaymentDto;
import com.hostel.model.Fees;
import com.hostel.model.HostelFeesDetails;
import com.hostel.service.impl.FeesPaymentServiceImpl;
import com.hostel.service.impl.HostelFeesServiceImpl;
import com.hostel.service.impl.HostellerDetailsServiceImpl;
import com.hostel.service.impl.UserServiceImpl;

@Controller
public class FeesPaymentController {
    
    @Autowired FeesPaymentServiceImpl feesPaymentService;
    @Autowired HostelFeesServiceImpl hostelFeesService;
    @Autowired UserServiceImpl userService;
    @Autowired HostellerDetailsServiceImpl hostellerDetailsService;

    public int loggedInUser(Principal principal){
        try{
            return hostellerDetailsService.findByUserId(userService.findByEmail(principal.getName()).getUserId()).getHostellerId();
        }
        catch(Exception e){
            return 0;
        }
    }
    @PostMapping("/payment")
    public String payFees(@ModelAttribute("paymentInfo") FeesPaymentDto feesPaymentDto, Principal principal, RedirectAttributes attributes) {
        try{
            if(feesPaymentDto.getHostellerId()==0 && loggedInUser(principal) != 0){
                feesPaymentDto.setHostellerId(loggedInUser(principal));
            }
            feesPaymentService.payFees(feesPaymentDto.getAmount(), feesPaymentDto.getHostellerId());
            attributes.addFlashAttribute("success", "payment successfull !");
        }
        catch (Exception e){
            attributes.addFlashAttribute("failed", "payment failed!");
        }
        return "redirect:/payment";
    }
    @GetMapping("/payment")
    public String getPaymentPage(Model model){
        model.addAttribute("paymentInfo", new FeesPaymentDto());
        return "makePayment";
    }
    
    @GetMapping("/getfees")
    public HostelFeesDetails getFees() {
        return hostelFeesService.getHostelFeesDetails();
    }

    @GetMapping("/fee_receipt")
    public String reqestFeeReceipt(Model model, Principal principal){
        List<Fees> fees = new ArrayList<>();
        if(loggedInUser(principal)!=0){
            fees.addAll(feesPaymentService.getAllPaymentRecords(loggedInUser(principal)));
        }
        model.addAttribute("feeReceipt", fees);
        model.addAttribute("hostellerId", new FeesPaymentDto().getHostellerId());
        return "feeReceipt";
    }

    @PostMapping("/fee_receipt")
    public String getFeeReceipt(@ModelAttribute("hostellerId") int hostellerId, Model model){
        model.addAttribute("feeReceipt", feesPaymentService.getAllPaymentRecords(hostellerId));
        return "feeReceipt";
    }
    
}
