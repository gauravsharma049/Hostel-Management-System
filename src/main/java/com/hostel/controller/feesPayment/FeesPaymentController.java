package com.hostel.controller.feesPayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.dto.FeesPaymentDto;
import com.hostel.model.Fees;
import com.hostel.model.HostelFeesDetails;
import com.hostel.service.impl.FeesPaymentServiceImpl;
import com.hostel.service.impl.HostelFeesServiceImpl;

@Controller
public class FeesPaymentController {
    
    @Autowired FeesPaymentServiceImpl feesPaymentService;
    @Autowired HostelFeesServiceImpl hostelFeesService;

    @PostMapping("/payment")
    public String payFees(@ModelAttribute("paymentInfo") FeesPaymentDto feesPaymentDto) {
        feesPaymentService.payFees(feesPaymentDto.getAmount(), feesPaymentDto.getHostellerId());
        return "makePayment";
    }
    @GetMapping("/payment")
    public String getPaymentPage(Model model){
        model.addAttribute("paymentInfo", new FeesPaymentDto());
        return "makePayment";
    }
    @PostMapping("/updatehostelfee")
    public String updateFees(@ModelAttribute("totalFeesAmount") Double amount) {
       hostelFeesService.updateHostelFeesDetails(amount);
       return "changeHostelFees";
    }
    @GetMapping("/updatehostelfee")
    public String getUpdateHostelFeePage(Model model){
        model.addAttribute("totalFeesAmount", new HostelFeesDetails().getTotalFeesAmount());
        return "changeHostelFees";
    }
    @GetMapping("/getfees")
    public HostelFeesDetails getFees() {
        return hostelFeesService.getHostelFeesDetails();
    }

    @GetMapping("/fee_receipt")
    public String reqestFeeReceipt(Model model){
        model.addAttribute("feeReceipt", new Fees());
        model.addAttribute("hostellerId", new FeesPaymentDto().getHostellerId());
        return "feeReceipt";
    }

    @PostMapping("/fee_receipt")
    public String getFeeReceipt(@ModelAttribute("hostellerId") int hostellerId, Model model){
        model.addAttribute("feeReceipt", feesPaymentService.getAllPaymentRecords(hostellerId));
        return "feeReceipt";
    }
    
}
