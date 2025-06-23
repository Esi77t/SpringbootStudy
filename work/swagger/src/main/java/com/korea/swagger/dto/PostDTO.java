package com.korea.swagger.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description="게시글 정보 DTO")
@Data
@AllArgsConstructor
public class PostDTO {
	@Schema(description="게시글 ID", example="1")
	private Long id;
	@Schema(description="게시글 제목", example="첫번째 글")
	private String title;
}
