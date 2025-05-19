package com.example.signup.dto;

import com.example.signup.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private long id;
	private String username;
	private String password;
	private String address;
	private String email;
	
	public UserDTO(UserEntity entity) {
		this.id = entity.getId();
		this.username = entity.getUsername();
		this.password = entity.getPassword();
		this.address = entity.getAddress();
		this.email = entity.getEmail();
	}
	
	public static UserEntity toEntity(UserDTO dto) {
		return UserEntity.builder()
				.id(dto.getId())
				.username(dto.getUsername())
				.password(dto.getPassword())
				.address(dto.getAddress())
				.email(dto.getEmail())
				.build();
	}
	
}
