package az.bughunteraz.service;

import az.bughunteraz.dto.UserDto;
import az.bughunteraz.dto.response.UserResponse;
import az.bughunteraz.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponse registerUser(UserDto userDto, Role role);


}
