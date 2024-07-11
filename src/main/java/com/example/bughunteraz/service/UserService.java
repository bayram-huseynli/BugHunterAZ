package com.example.bughunteraz.service;

import com.example.bughunteraz.dto.CompanyDto;
import com.example.bughunteraz.dto.HackerDto;
import com.example.bughunteraz.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponse registerHacker(HackerDto hackerDto);

    UserResponse registerCompany(CompanyDto companyDto);

}
