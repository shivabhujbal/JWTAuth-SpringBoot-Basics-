package com.excelr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excelr.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


	Optional<User> findByUsername(String username);
}
