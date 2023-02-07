package com.hostel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Warden {
    private int wardenId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String gender;
    @OneToOne(fetch = FetchType.EAGER)
    private Hostel hostel;
}
