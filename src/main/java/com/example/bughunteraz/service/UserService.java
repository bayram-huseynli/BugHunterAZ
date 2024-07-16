package com.example.bughunteraz.service;

import com.example.bughunteraz.dto.UserDto;
import com.example.bughunteraz.dto.response.UserResponse;
import com.example.bughunteraz.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponse registerUser(UserDto userDto, Role role);


}
