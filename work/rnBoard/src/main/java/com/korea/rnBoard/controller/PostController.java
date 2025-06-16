package com.korea.rnBoard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.rnBoard.dto.PostDTO;
import com.korea.rnBoard.dto.ResponseDTO;
import com.korea.rnBoard.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController //화면은 리액트로 구성을 하고 있으니까
//@ResponseBody + @Controller
//ㄴ 반환값을 그대로 클라이언트에게 전달
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {

	private final PostService service;
	
	//전체 조회
	@GetMapping
	public ResponseEntity<?> getAllPosts(){
		List<PostDTO> list = service.getAllPosts();
		ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().list(list).build();
		return ResponseEntity.ok(response);
	}
	
	//한건 조회
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		List<PostDTO> list = service.findById(id);
		ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().list(list).build();
		return ResponseEntity.ok(response);
	}
	
	//게시글 추가
	@PostMapping
	public ResponseEntity<?> createPost(@RequestBody PostDTO dto){
		List<PostDTO> list = service.createPost(dto);
		ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().list(list).build();
		return ResponseEntity.ok(response);
	}
	
	
}







