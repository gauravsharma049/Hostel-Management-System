package com.hostel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hostel_id",referencedColumnName = "hostel_id")
    private Hostel hostel;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Override
    public String toString() {
        return "Warden [wardenId=" + wardenId + ", name=" + name + ", phone=" + phone + ", email=" + email
                + ", address=" + address + ", gender=" + gender + ", hostel="  + ", user="+ user  + "]";
    }

    
}
