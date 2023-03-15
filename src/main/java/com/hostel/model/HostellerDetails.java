package com.hostel.model;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hostellerDetails")
    private List<Fees> feesList;
}
