package com.hostel.service;

import com.hostel.dto.UserDto;
import com.hostel.model.User;

import java.util.List;

public interface UserService {

    User save(UserDto userDto);
    User update(UserDto userDto);
    List<UserDto> getAllUser();
    void delete(int id);
}
