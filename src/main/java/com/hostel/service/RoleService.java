package com.hostel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.model.Role;
import com.hostel.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired RoleRepository roleRepository;

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
