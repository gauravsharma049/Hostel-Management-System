package com.hostel.dto;

import com.hostel.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int userId;
    @Size(min = 3, max = 25, message = "Name should be 3-25 Characters !")
    private String name;
    @Size(min = 10, max = 15, message = "Enter valid Mobile NO. !")
    private String mobile;
    @NotEmpty(message = "Enter valid Email !")
    private String email;
    @Size(min = 5, max = 15, message = "Password should be 5-15 Characters !")
    private String password;
    private List<Role> roles;
}
