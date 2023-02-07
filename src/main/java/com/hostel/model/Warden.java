package com.hostel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Warden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wardenId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String gender;
    @OneToOne(fetch = FetchType.EAGER)
    private Hostel hostel;
}
