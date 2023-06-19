package com.hostel.service.impl;

import com.hostel.model.User;
import com.hostel.repository.UserRepository;
import com.hostel.service.PasswordChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordChangeImpl implements PasswordChange {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Override
    public void resetPassword(int userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).get();
        if(user.getPassword().equals(passwordEncoder.encode(oldPassword))){
            user.setPassword(passwordEncoder.encode(newPassword));
            System.out.println("password changed");
        }
        else{
            System.out.println("password does not match");
            System.out.println("old password:- "+user.getPassword());
            System.out.println("old password entered:- "+ passwordEncoder.encode(oldPassword));
            System.out.println("new password entered:- "+passwordEncoder.encode(newPassword));
            System.out.println("confirm password entered:- "+passwordEncoder.encode(newPassword));
        }
    }

    @Override
    public void forgotPassword() {

    }
}
