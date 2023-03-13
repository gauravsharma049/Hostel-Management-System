package com.hostel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.model.Fees;

public interface FeesRepository extends JpaRepository<Fees, String>{
   public List<Fees> findByHostellerDetailsHostellerId(int hostellerId);
}
