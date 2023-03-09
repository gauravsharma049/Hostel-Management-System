package com.hostel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.service.impl.HostellerDetailsServiceImpl;

@Service
public class ProfileService {
    @Autowired HostellerDetailsServiceImpl hostellersService;
    
    public void getProfileInfo(int hostellerId){
      
        return;
    }
}
