package com.hostel.service.impl;

import com.hostel.dto.UserDto;
import com.hostel.model.Role;
import com.hostel.model.User;
import com.hostel.model.Warden;
import com.hostel.repository.UserRepository;
import com.hostel.repository.WardenRepository;
import com.hostel.service.RoleService;
import com.hostel.service.WardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class WardenServiceImpl implements WardenService {

    @Autowired
    private WardenRepository wardenRepository;
    @Autowired UserServiceImpl userService;
    @Autowired RoleService roleService;
    @Autowired BCryptPasswordEncoder passwordEncoder;

    @Override
    public Warden save(Warden warden) {
        User user = warden.getUser();
        user.setPassword(passwordEncoder.encode(user.getEmail()));
        System.out.println(user);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.save(new Role(2, "ROLE_warden")));

        return wardenRepository.save(warden);
    }

    @Override
    public Warden update(Warden warden) {
        Warden wardenUpdate = wardenRepository.findById(warden.getWardenId()).get();
        wardenUpdate.setName(warden.getName());
        wardenUpdate.setEmail(warden.getEmail());
        wardenUpdate.setPhone(warden.getPhone());
        wardenUpdate.setAddress(warden.getAddress());
        wardenUpdate.setGender(warden.getGender());
        wardenUpdate.setHostel(warden.getHostel());
        return wardenRepository.save(wardenUpdate);
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
