package com.hostel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.model.HostelFeesDetails;
import com.hostel.repository.HostelFeesDetailsRepository;
import com.hostel.service.HostelFeesService;

@Service
public class HostelFeesServiceImpl implements HostelFeesService{
    @Autowired HostelFeesDetailsRepository hostelFeesDetailsRepository;

    @Override
    public HostelFeesDetails getHostelFeesDetails() {
        try{

            return hostelFeesDetailsRepository.findAll().get(0);
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public void updateHostelFeesDetails(double amount) {
        if(getHostelFeesDetails()==null) {
            HostelFeesDetails hostelFeesDetails = new HostelFeesDetails();
            hostelFeesDetails.setId(1);
            hostelFeesDetails.setTotalFeesAmount(amount);
            hostelFeesDetailsRepository.save(hostelFeesDetails);
            return;
        }
        
        HostelFeesDetails hostelFeesDetails = hostelFeesDetailsRepository.findById(1).get();
        hostelFeesDetails.setTotalFeesAmount(amount);
        hostelFeesDetailsRepository.save(hostelFeesDetails);
        
    }
}
