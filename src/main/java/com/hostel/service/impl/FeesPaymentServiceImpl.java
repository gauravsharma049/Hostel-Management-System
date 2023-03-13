package com.hostel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.dto.HostellerDetailsDto;
import com.hostel.model.Fees;
import com.hostel.model.HostellerDetails;
import com.hostel.repository.FeesRepository;
import com.hostel.service.FeesPaymentService;

@Service
public class FeesPaymentServiceImpl implements FeesPaymentService{
    @Autowired FeesRepository feesRepository;
    @Autowired HostellerDetailsServiceImpl hostellerDetailsService;
    @Override
    public void payFees(double amount, int hostellerId) {
        HostellerDetails hostellerDetails = hostellerDetailsService.findById(hostellerId);
        String paymentId = "PAY"+hostellerId+new Date().getTime();
        Fees fees = new Fees();
        fees.setPaymentId(paymentId);
        fees.setPaymentAmount(amount);
        fees.setHostellerDetails(hostellerDetails);
        fees.setPaymentStatus(true);
        fees.setPaymentDateAndTime(new Date().toString());
        
        feesRepository.save(fees);
        HostellerDetails h = hostellerDetailsService.findById(hostellerId);
        double dueFeesAmount = h.getDueFeesAmount();
        h.setDueFeesAmount(dueFeesAmount-amount);
        h.setPaidFeesAmount(h.getTotalFeesAmount()-h.getDueFeesAmount());
        HostellerDetailsDto hostellerDetailsDto = new HostellerDetailsDto();
        hostellerDetailsDto.setPaidFeesAmount(h.getPaidFeesAmount());
        hostellerDetailsDto.setDueFeesAmount(h.getDueFeesAmount());
        hostellerDetailsService.update(hostellerId, hostellerDetailsDto);
    }

    @Override
    public Fees getPaymentDetails(String paymentId) {
        return feesRepository.findById(paymentId).get();
    }

    @Override
    public List<Fees> getAllPaymentRecords(int hostellerId) {
        return feesRepository.findByHostellerDetailsHostellerId(hostellerId);
    }
    
}
