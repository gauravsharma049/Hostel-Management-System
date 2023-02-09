package com.hostel.service.impl;

import com.hostel.model.Hostel;
import com.hostel.repository.HostelRepository;
import com.hostel.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HostelServiceImpl implements HostelService {

    @Autowired
    private HostelRepository hostelRepository;

    @Override
    public Hostel save(Hostel hostel) {
        return hostelRepository.save(hostel);
    }

    @Override
    public Hostel update(Hostel hostel) {
        Hostel hostelUpdate = null;
        hostelUpdate = hostelRepository.findById(hostel.getHostelId()).get();
        hostelUpdate.setName(hostel.getName());
        hostelUpdate.setType(hostel.getType());
        hostelUpdate.setNoOfRoom(hostel.getNoOfRoom());
        hostelUpdate.setAddress(hostel.getAddress());
        hostelUpdate.setAnnualExp(hostel.getAnnualExp());
        return hostelRepository.save(hostelUpdate);
    }

    @Override
    public Hostel findById(int id) {
        return hostelRepository.findById(id).get();
    }

    @Override
    public List<Hostel> findAllHostel() {
        return hostelRepository.findAll();
    }

    @Override
    public void delete(int id) {
        Hostel hostel = hostelRepository.findById(id).get();
        hostelRepository.delete(hostel);
    }
}
