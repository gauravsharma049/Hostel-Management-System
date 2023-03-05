package com.hostel.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hostel_id")
    private int hostelId;
    @Size(min = 3, max = 10, message = "Name should be 3-10 Characters !")
    private String name;
    private String type;
    private String noOfRoom;
    private String address;
    private String annualExp;
    @OneToOne(mappedBy = "hostel")
    private Warden warden;
}
