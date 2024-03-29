package com.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
