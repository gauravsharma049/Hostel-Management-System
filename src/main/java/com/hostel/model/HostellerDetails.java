package com.hostel.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HostellerDetails {
    @Id
    private int hostellerId;
    private String hostelNumber;
    private String roomNumber;
    private String entryDate;
    private String exitDate;
    private String address;
    private String identityType;
    private String identityNumber;
    //is available or left the hostel
    private boolean hostellerStatus;
    private double totalFeesAmount;
    private double paidFeesAmount;
    private double dueFeesAmount;
    private boolean feesStatus;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
