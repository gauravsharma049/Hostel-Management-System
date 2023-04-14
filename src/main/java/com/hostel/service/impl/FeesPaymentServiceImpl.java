package com.hostel.service.impl;

import java.util.Date;
import java.util.List;

import com.hostel.dto.FeesPaymentDto;
import com.hostel.service.HostellerDetailsService;
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
    @Autowired
    HostellerDetailsService hostellerDetailsService;
    @Override
    public void payFees(FeesPaymentDto feesPaymentDto) {
        HostellerDetails hostellerDetails = hostellerDetailsService.findById(feesPaymentDto.getHostellerId());
        String paymentId = feesPaymentDto.getPaymentId();
        Fees fees = new Fees();
        fees.setPaymentId(paymentId);
        fees.setPaymentAmount(feesPaymentDto.getAmount());
        fees.setHostellerDetails(hostellerDetails);
        fees.setPaymentStatus(true);
        fees.setPaymentDateAndTime(new Date().toString());
        System.out.println(fees);
        feesRepository.save(fees);
        System.out.println("in pay fees function");
        HostellerDetails h = hostellerDetailsService.findById(feesPaymentDto.getHostellerId());
        double dueFeesAmount = h.getDueFeesAmount();
        h.setDueFeesAmount(dueFeesAmount-feesPaymentDto.getAmount());
        h.setPaidFeesAmount(h.getTotalFeesAmount()-h.getDueFeesAmount());
        HostellerDetailsDto hostellerDetailsDto = new HostellerDetailsDto();
        hostellerDetailsDto.setPaidFeesAmount(h.getPaidFeesAmount());
        hostellerDetailsDto.setDueFeesAmount(h.getDueFeesAmount());
        hostellerDetailsService.update(feesPaymentDto.getHostellerId(), hostellerDetailsDto);
        System.out.println("fees paid");
    }

    @Override
    public Fees getPaymentDetails(String paymentId) {
        return feesRepository.findByPaymentId(paymentId);
    }

    @Override
    public List<Fees> getAllPaymentRecords(int hostellerId) {
        return feesRepository.findByHostellerDetailsHostellerId(hostellerId);
    }
    
}
