package com.hostel.service;

import com.hostel.dto.UserDto;
import com.hostel.model.User;

public interface UserService {

    User save(UserDto userDto);
}
