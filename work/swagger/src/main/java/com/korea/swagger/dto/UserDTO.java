package com.korea.swagger.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description="사용자 정보 DTO")
@Data
@AllArgsConstructor
public class UserDTO {
	
	@Schema(description="사용자 ID", example="1")
	private Long id;
	
	@Schema(description="사용자 이름", example="홍길동")
	private String name;
	
	public UserDTO() {};
}
