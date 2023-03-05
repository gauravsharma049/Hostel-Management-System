package com.hostel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fees {
    @Id
    private String paymentId;
    private String paymentType;
    private String paymentDateAndTime;
    private double paymentAmount;
    private boolean paymentStatus;

    @ManyToOne
    private HostellerDetails hostellerDetails;

}
