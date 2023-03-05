package com.hostel.service;

import com.hostel.model.Warden;

import java.util.List;

public interface WardenService {

    Warden save(Warden warden);
    Warden update(Warden warden);
    Warden findById(int id);
    List<Warden> findAllWarden();
    void delete(int id);
}
