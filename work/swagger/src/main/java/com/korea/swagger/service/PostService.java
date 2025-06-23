package com.korea.swagger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.korea.swagger.dto.PostDTO;

@Service
public class PostService {
	
	// 게시글을 저장하는 DB라고 가정
	private final Map<Long, PostDTO> postMap = new HashMap<>();
	private Long nextId = 1L;
	
	// 기본 생성자(프로그램이 실행될 때 자동으로 실행)
	public PostService() {
		savePost(new PostDTO(null, "첫 번째 게시글"));
		savePost(new PostDTO(null, "두 번째 게시글"));
	}
	
	public List<PostDTO> getAllPosts() {
		return new ArrayList<>(postMap.values());
	}
	
	public PostDTO getPostById(Long id) {
		return new PostDTO(id, "게시글" + id);
	}
	
	public PostDTO savePost(PostDTO postDto) {
		postDto.setId(nextId++);
		postMap.put(postDto.getId(), postDto);
		
		return postDto;
	}
	
	public void deletePostById(Long id) {
		
	}
}
