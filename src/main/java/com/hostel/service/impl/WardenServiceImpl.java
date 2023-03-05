package com.hostel.service.impl;

import com.hostel.model.Warden;
import com.hostel.repository.WardenRepository;
import com.hostel.service.WardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WardenServiceImpl implements WardenService {

    @Autowired
    private WardenRepository wardenRepository;

    @Override
    public Warden save(Warden warden) {
        return wardenRepository.save(warden);
    }

    @Override
    public Warden update(Warden warden) {
        return null;
    }

    @Override
    public Warden findById(int id) {
        return wardenRepository.findById(id).get();
    }

    @Override
    public List<Warden> findAllWarden() {
        return wardenRepository.findAll();
    }

    @Override
    public void delete(int id) {
        Warden warden = wardenRepository.findById(id).get();
        wardenRepository.delete(warden);
    }
}
