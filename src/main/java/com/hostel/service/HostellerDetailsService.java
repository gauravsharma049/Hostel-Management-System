package com.hostel.service;

import java.util.List;

import com.hostel.dto.HostellerDetailsDto;
import com.hostel.model.HostellerDetails;

public interface HostellerDetailsService {
    HostellerDetails save(HostellerDetailsDto hostellerDetails);
    HostellerDetails update(int id, HostellerDetailsDto hostellerDetails);
    HostellerDetails findById(int id);
    List<HostellerDetailsDto> findAllHostellerDetails();
    void delete(int id);

    HostellerDetails findByUserId(int userId);
}
