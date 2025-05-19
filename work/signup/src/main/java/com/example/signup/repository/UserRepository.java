package com.example.signup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.signup.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	boolean existByUsername(String username);
}
