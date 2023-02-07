package com.hostel.service.impl;

import com.hostel.dto.UserDto;
import com.hostel.model.User;
import com.hostel.repository.UserRepository;
import com.hostel.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(UserDto userDto) {
            User user = new User();
            user.setName(userDto.getName());
            user.setMobile(userDto.getMobile());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRoles(userDto.getRoles());
            return userRepository.save(user);
    }

    @Override
    public User update(UserDto userDto) {
        User user =userRepository.findById(userDto.getUserId()).get();
        user.setName(userDto.getName());
        user.setMobile(userDto.getMobile());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for(User user: users){
            UserDto userDto = new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setMobile(user.getMobile());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public void delete(int id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

}
