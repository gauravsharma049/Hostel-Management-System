package com.hostel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired HostellerDetailsService hostellersService;
    
    public void getProfileInfo(int hostellerId){
      
        return;
    }
}
