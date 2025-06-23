package com.korea.swagger.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.swagger.dto.UserDTO;
import com.korea.swagger.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name="User API", description="사용자 정보 관리 API")
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	
	@GetMapping
	@Operation(summary="모든 사용자 조회", description="전체 사용자 목록을 반환합니다")
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	@Operation(summary="사용자 단건 조회", description="ID로 사용자를 조회합니다.")
	public UserDTO getUserById(@Parameter(description="사용자 ID", example="1")  @PathVariable("id") Long id) {
		return userService.getUserById(id);
	}
}
