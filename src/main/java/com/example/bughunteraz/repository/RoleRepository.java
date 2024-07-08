package com.example.bughunteraz.repository;

import com.example.bughunteraz.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String roleHacker);
}
