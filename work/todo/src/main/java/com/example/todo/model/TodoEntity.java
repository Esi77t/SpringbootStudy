package com.example.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoEntity {
	private String id;	// 이 객체의 id
	private String userId;	// 이 객체를 생성한 유저의 아이디
	private String title;	// Todo의 타이틀
	private boolean done;	// Todo의 완료 여부
	
}
