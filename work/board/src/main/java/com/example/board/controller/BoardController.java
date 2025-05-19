package com.example.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.ResponseDTO;
import com.example.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService service;
	
	// 전체 조회
	@GetMapping("/all")
	public ResponseEntity<?> getAllPosts() {
		List<BoardDTO> list = service.getAllPosts();
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> createPost(@RequestBody BoardDTO dto) {
		List<BoardDTO> list = service.createPost(dto);
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost(@PathVariable long id) {
		boolean deleted = service.deletePost(id);
		
		return ResponseEntity.ok().body(deleted);
	}
	
	// id를 통한 게시글을 한 건 조회하기
	@GetMapping("/{id}")
	public ResponseEntity<?> findPostById(@PathVariable long id) {
		BoardDTO list = service.findPostById(id);
		
		return ResponseEntity.ok().body(list);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePost(@PathVariable long id, @RequestBody BoardDTO dto) {
		boolean putPost = service.upadatePost(id, dto);
		
		return ResponseEntity.ok().body(putPost);
	}
	
}
