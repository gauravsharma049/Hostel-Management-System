package com.hostel.controller.feesPayment;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hostel.dto.FeesPaymentDto;
import com.hostel.model.Fees;
import com.hostel.model.HostelFeesDetails;
import com.hostel.service.FeesPaymentService;
import com.hostel.service.HostelFeesService;
import com.hostel.service.HostellerDetailsService;
import com.hostel.service.UserService;

@Controller
public class FeesPaymentController {
    
    @Autowired
    FeesPaymentService feesPaymentService;
    @Autowired
    HostelFeesService hostelFeesService;
    @Autowired
    UserService userService;
    @Autowired
    HostellerDetailsService hostellerDetailsService;

    public int loggedInUser(Principal principal){
        try{
            return hostellerDetailsService.findByUserId(userService.findByEmail(principal.getName()).getUserId()).getHostellerId();
        }
        catch(Exception e){
            return 0;
        }
    }
    @PostMapping("/payment")
    @ResponseBody
    public Map<String, String> payFees(@RequestBody FeesPaymentDto feesPaymentDto, Principal principal, RedirectAttributes attributes) {
        System.out.println("payment controller");
        try{
            if(feesPaymentDto.getHostellerId()==0 && loggedInUser(principal) != 0){
                feesPaymentDto.setHostellerId(loggedInUser(principal));
            }
            if(feesPaymentDto.isPaymentStatus()){
                System.out.println("paying fees");
                feesPaymentService.payFees(feesPaymentDto);
                attributes.addFlashAttribute("success", "payment successfull !");
            }
            else{
                attributes.addFlashAttribute("failed", "payment failed!");
            }
        }
        catch (Exception e){
            attributes.addFlashAttribute("failed", "payment failed!");
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        return map;
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
