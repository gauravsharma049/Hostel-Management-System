package com.hostel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long feesModelId;
    private String paymentId;
    private String paymentType;
    private String paymentDateAndTime;
    private double paymentAmount;
    private boolean paymentStatus;

    @ManyToOne
    private HostellerDetails hostellerDetails;

}
