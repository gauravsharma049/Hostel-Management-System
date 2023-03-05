package com.hostel.service;

import java.util.List;

import com.hostel.model.Fees;

public interface FeesPaymentService {
    public void payFees(double amount, int hostellerId);
    public Fees getPaymentDetails(String paymentId);
    public List<Fees> getAllPaymentRecords(int hostellerId);

}
