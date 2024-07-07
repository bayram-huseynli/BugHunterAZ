package com.example.bughunteraz.service;

import com.example.bughunteraz.dto.request.CompanyRequest;
import com.example.bughunteraz.dto.request.HackerRequest;
import com.example.bughunteraz.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registerHacker(HackerRequest hacker);

    User registerCompany(CompanyRequest company);

    User findByEmail(String email);
}
