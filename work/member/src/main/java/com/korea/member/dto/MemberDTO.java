package com.korea.member.dto;

import com.korea.member.model.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	
	private int id;
	private String name;
	private String email;
	private String password;
	
	public static MemberDTO fromEntity(MemberEntity entity) {
		return MemberDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.email(entity.getEmail())
				.password(entity.getPassword())
				.build();
	}
	
	public static MemberEntity toEntity(MemberDTO dto) {
		return MemberEntity.builder()
				.id(dto.getId())
				.name(dto.getName())
				.email(dto.getEmail())
				.password(dto.getPassword())
				.build();
	}
}
