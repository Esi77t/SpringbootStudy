package com.korea.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDTO dto) {
		UserEntity entity = UserDTO.toEntity(dto);
		List<UserDTO> users = service.create(entity);
		return ResponseEntity.ok(users);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		List<UserDTO> users = service.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	// 이메일로 사용자 검색하기
	@GetMapping("/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
		UserDTO users = service.getUserByEmail(email);
		return ResponseEntity.ok(users);
	}
	
	// ID를 통해 이름과 이메일 수정하기
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody UserDTO dto) {
		UserEntity entity = UserDTO.toEntity(dto);
		List<UserDTO> users = service.updateUser(entity);
		return ResponseEntity.ok(users);
	}
	
}
