package com.korea.user.dto;

import com.korea.user.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 클라이언트와 주고받을 때(요청, 응답) 데이터를 DTO에 담아서 주자
// 데이터가 계층간 이동할 때 controller -> service -> repostiory
// Entity에 담아서 옮기자

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
	private int id;
	private String name;
	private String email;
	
	// Entity -> DTO 변환
	public UserDTO(UserEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
	}
	
	// DTO -> Entity 변환
	public static UserEntity toEntity(UserDTO dto) {
		return UserEntity.builder()
				.id(dto.getId())
				.name(dto.getName())
				.email(dto.getEmail())
				.build();
	}
}
