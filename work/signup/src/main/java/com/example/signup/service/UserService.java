package com.example.signup.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.signup.dto.UserDTO;
import com.example.signup.model.UserEntity;
import com.example.signup.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository repository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserDTO registerUser(UserDTO dto) {
		if(repository.existByUsername(dto.getUsername())) {
			throw new RuntimeException("이미 사용중인 아이디입니다");
		}
		
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		UserEntity entity = repository.save(UserDTO.toEntity(dto));
		
		return new UserDTO(entity);
	}
}
