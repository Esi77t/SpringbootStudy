package com.example.board.dto;

import java.time.LocalDateTime;

import com.example.board.model.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private long id;
	private String title;
	private String author;
	private LocalDateTime writingTime;
	private String content;
	
	public static BoardDTO fromEntity(BoardEntity entity) {
		return BoardDTO.builder()
				.id(entity.getId())
				.title(entity.getTitle())
				.author(entity.getAuthor())
				.writingTime(entity.getWritingTime())
				.content(entity.getContent())
				.build();
	}
	
	public static BoardEntity fromDTO(BoardDTO dto) {
		return BoardEntity.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.author(dto.getAuthor())
				.writingTime(dto.getWritingTime())
				.content(dto.getContent())
				.build();
	}
}
