package com.hostel.service;

import java.util.List;

import com.hostel.dto.FeesPaymentDto;
import com.hostel.model.Fees;

public interface FeesPaymentService {
    public void payFees(FeesPaymentDto feesPaymentDto);
    public Fees getPaymentDetails(String paymentId);
    public List<Fees> getAllPaymentRecords(int hostellerId);

}
