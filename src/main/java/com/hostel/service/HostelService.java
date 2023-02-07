package com.hostel.service;

import com.hostel.model.Hostel;

import java.util.List;

public interface HostelService {
    Hostel save(Hostel hostel);
    Hostel update(Hostel hostel);
    List<Hostel> findAllHostel();
    void delete(int id);
}
