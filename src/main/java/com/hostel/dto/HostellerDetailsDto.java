package com.hostel.dto;

import java.util.List;

import com.hostel.model.Role;
import com.hostel.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HostellerDetailsDto {
    private int userId;
    private String name;
    private String mobile;
    private String email;
    private String password;
    private List<Role> roles;

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
    
    private User user;

    // public boolean getHostellerStatus() {
    //     return hostellerStatus;
    // }
}
