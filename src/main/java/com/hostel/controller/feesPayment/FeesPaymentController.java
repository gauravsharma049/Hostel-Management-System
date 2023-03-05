package com.hostel.controller.feesPayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.dto.FeesPaymentDto;
import com.hostel.model.HostelFeesDetails;
import com.hostel.service.impl.FeesPaymentServiceImpl;
import com.hostel.service.impl.HostelFeesServiceImpl;

@RestController
public class FeesPaymentController {
    
    @Autowired FeesPaymentServiceImpl feesPaymentService;
    @Autowired HostelFeesServiceImpl hostelFeesService;

    @PostMapping("/payFees")
    public String payFees(@RequestBody FeesPaymentDto feesPaymentDto) {
        feesPaymentService.payFees(feesPaymentDto.getAmount(), feesPaymentDto.getHostellerId());
        return "Fees paid successfully";
    }

    @PostMapping("/updatefees")
    public String updateFees(@RequestBody HostelFeesDetails hostelFeesDetails) {
       hostelFeesService.updateHostelFeesDetails(hostelFeesDetails.getTotalFeesAmount());
       return "Fees updated successfully";
    }

    @GetMapping("/getfees")
    public HostelFeesDetails getFees() {
        return hostelFeesService.getHostelFeesDetails();
    }

    
}
