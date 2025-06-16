package com.korea.rnBoard.dto;

import com.korea.rnBoard.domain.PostEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data //setter, getter
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 갖는 생성자
public class PostDTO {

	private Long id;
	private String title;
	private String author;
	private String description;
	private String time;
	private int views;
	
	//entity -> dto
	public static PostDTO fromEntity(PostEntity entity) {
		return PostDTO.builder()
				.id(entity.getId())
				.title(entity.getTitle())
				.author(entity.getAuthor())
				.description(entity.getDescription())
				.time(entity.getTime())
				.views(entity.getViews())
				.build();
	}
	
	//dto -> entity
	public static PostEntity toEntity(PostDTO dto) {
		return PostEntity.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.author(dto.getAuthor())
				.description(dto.getDescription())
				.time(dto.getTime())
				.views(dto.getViews())
				.build();
	}
}
