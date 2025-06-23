package com.korea.swagger.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.korea.swagger.dto.UserDTO;

@Service
public class UserService {
	
	public List<UserDTO> getAllUsers() {
		return Arrays.asList(
					new UserDTO(1L, "홍길동"),
					new UserDTO(2L, "김철수")
				);
	}
	
	public UserDTO getUserById(Long id) {
		return new UserDTO(id, "사용자" + id);
	}
}
