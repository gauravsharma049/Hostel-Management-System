package com.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.model.HostellerDetails;

public interface HostellerDetailsRepository extends JpaRepository<HostellerDetails, Integer>{
    HostellerDetails findByUserUserId(int id);
}
