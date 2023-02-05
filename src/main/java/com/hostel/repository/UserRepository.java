package com.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    public User findByEmail(String email);
}
