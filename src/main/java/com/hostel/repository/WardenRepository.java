package com.hostel.repository;

import com.hostel.model.Warden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardenRepository extends JpaRepository<Warden,Integer> {
}
