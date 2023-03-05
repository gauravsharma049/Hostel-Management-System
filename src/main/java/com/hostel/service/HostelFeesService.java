package com.hostel.service;

import com.hostel.model.HostelFeesDetails;

public interface HostelFeesService {
    public HostelFeesDetails getHostelFeesDetails();
    public void updateHostelFeesDetails(double amount);
    
}
