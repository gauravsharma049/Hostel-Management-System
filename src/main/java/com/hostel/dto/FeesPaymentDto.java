package com.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeesPaymentDto {
    private double amount;
    private int hostellerId;
    private boolean paymentStatus;
    private String paymentId;
}
