package az.bughunteraz.controller.auth;

import az.bughunteraz.entity.Role;
import az.bughunteraz.dto.request.company.CompanyRequest;
import az.bughunteraz.dto.request.hacker.HackerRequest;
import az.bughunteraz.dto.response.UserResponse;
import az.bughunteraz.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/hacker")
    public ResponseEntity<UserResponse> registerHacker(@Validated @RequestBody HackerRequest hackerDto) {
        return new ResponseEntity<>(userService.registerUser(hackerDto, Role.HACKER), HttpStatus.CREATED);
    }

    @PostMapping("/company")
    public ResponseEntity<UserResponse> registerCompany(@Validated @RequestBody CompanyRequest companyDto) {
        return new ResponseEntity<>(userService.registerUser(companyDto, Role.COMPANY), HttpStatus.CREATED);
    }
}
