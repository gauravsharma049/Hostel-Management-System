package com.hostel.controller.feesPayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.dto.FeesPaymentDto;
import com.hostel.model.HostelFeesDetails;
import com.hostel.service.FeesPaymentService;
import com.hostel.service.HostelFeesService;
import com.hostel.service.HostellerDetailsService;
import com.hostel.service.UserService;

@RestController
@RequestMapping("/fees")
public class FeesPaymentRestController {
    
    @Autowired
    FeesPaymentService feesPaymentService;
    @Autowired
    HostelFeesService hostelFeesService;
    @Autowired
    UserService userService;
    @Autowired
    HostellerDetailsService hostellerService;

    @PostMapping("/payFees")
    public String payFees(@RequestBody FeesPaymentDto feesPaymentDto) {
        feesPaymentService.payFees(feesPaymentDto);
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

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") int id){
        hostellerService.delete(id);
    }

}
