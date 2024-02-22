package com.excelr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excelr.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
